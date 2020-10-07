package com.epam.project.db.service;

import com.epam.project.db.dao.ConnectionPool;
import com.epam.project.db.dao.UserDAO;
import com.epam.project.db.dao.UserInfoDAO;
import com.epam.project.db.entity.User;
import com.epam.project.db.entity.UserInfo;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService extends Service {

    private static final Logger log = Logger.getLogger(AccountService.class);

    AccountService() {

    }

    public static void insertAccount(User user, UserInfo userInfo) throws DBException, SQLException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        try {
            con.setAutoCommit(false);
            UserDAO.insertUser(con, user);
            UserInfoDAO.insertUserInfo(con, userInfo);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new DBException();
        }
        finally {
            close(con);
        }
    }

}
