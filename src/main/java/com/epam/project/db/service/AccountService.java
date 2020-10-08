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
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();

        try {
            log.debug("Starting transaction");
            con.setAutoCommit(false);

            log.debug("Inserting user");
            UserDAO.insertUser(con, user);

            log.debug("Get user ID from db");
            user = UserDAO.findUser(con, user.getLogin());
            log.debug("User ->" + user);
            userInfo.setId(user.getId());

            log.debug("Inserting user info -> " + userInfo);
            UserInfoDAO.insertUserInfo(con, userInfo);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(con);
        }
    }

}
