package com.epam.project.db.dao;

import com.epam.project.db.EntityMapper;
import com.epam.project.db.bean.AccountBean;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends DAO{
    private static final Logger log = Logger.getLogger(AccountDAO.class);

    private AccountDAO() {

    }

    private static final String SQL_FIND_ALL_INFO_ABOUT_USERS = "SELECT u.login,u.role_id,u.locale,ui.name,ui.surname,ui.gender,ui.email,ui.city" +
            "FROM users u" +
            "JOIN users_info ui on u.id = ui.id";

    private static final String SQL_FIND_ALL_INFO_ABOUT_USERS_WITH_ORDERS_COUNT = "WITH ct as (" +
            "Select user_id, count(1) c,sum(price) p from tours group by user_id) " +
            "SELECT u.login,u.role_id,u.locale,ui.name,ui.surname,ui.gender,ui.email,ui.city, ct.c,ct.p " +
            "FROM users u " +
            "         JOIN users_info ui on u.id = ui.id" +
            "          left Join ct on u.id = ct.user_id";

    public static List<AccountBean> findAllAccounts(Connection con) throws DBException {
        List<AccountBean> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            AccountMapper mapper = new AccountMapper();
            stmt = con.prepareStatement(SQL_FIND_ALL_INFO_ABOUT_USERS_WITH_ORDERS_COUNT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(mapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(stmt, rs);
        }
        return result;
    }

    private static class AccountMapper implements EntityMapper<AccountBean> {

        @Override
        public AccountBean mapRow(ResultSet rs) {
            try {
                AccountBean accBean = new AccountBean();
                accBean.setLogin(rs.getString("u.login"));
                accBean.setRoleId(rs.getInt("u.role_id"));
                accBean.setLocale(rs.getString("u.locale"));
                //accBean.setIsBlocked();
                accBean.setName(rs.getString("ui.name"));
                accBean.setSurname(rs.getString("ui.surname"));
                accBean.setGender(rs.getString("ui.gender"));
                accBean.setEmail(rs.getString("ui.email"));
                accBean.setCity(rs.getString("ui.city"));
                accBean.setOrdersCount(rs.getInt("ct.c"));
                accBean.setOrdersPrice(rs.getInt("ct.p"));
                return accBean;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
