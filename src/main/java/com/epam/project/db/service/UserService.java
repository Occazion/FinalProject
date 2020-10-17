package com.epam.project.db.service;


import com.epam.project.db.dao.ConnectionPool;
import com.epam.project.db.dao.UserDAO;
import com.epam.project.db.dao.UserInfoDAO;
import com.epam.project.db.entity.User;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class UserService extends Service{

    private UserService() {

    }

        private static final Logger log = Logger.getLogger(UserService.class);

        public static void insertUser(User user) throws DBException {
            ConnectionPool conPool = ConnectionPool.getInstance();
            log.debug("Obtaining connection");
            Connection con = conPool.getConnection();
            try{
                log.debug("Inserting user");
                UserDAO.insertUser(con,user);
            } catch (DBException e) {
                log.error(e.getMessage());
                throw new DBException(e.getMessage(), e);
            }
            finally {
                close(con);
            }
        }

        public static User findUser(String login) throws DBException {
            User user;
            ConnectionPool conPool = ConnectionPool.getInstance();
            log.debug("Obtaining connection");
            Connection con = conPool.getConnection();
            try{
                log.debug("Finding user");
                user = UserDAO.findUser(con,login);
            } catch (DBException e) {
                log.error(e.getMessage());
                throw new DBException(e.getMessage(), e);
            }
            finally {
                close(con);
            }
            return user;
        }

        public static List<User> findAllUsers() throws DBException {
            List<User> userList;
            ConnectionPool conPool = ConnectionPool.getInstance();
            log.debug("Obtaining connection");
            Connection con = conPool.getConnection();
            try{
                log.debug("Finding all users");
                userList = UserDAO.findAllUsers(con);
            } catch (DBException e) {
                log.error(e.getMessage());
                throw new DBException(e.getMessage(), e);
            }
            finally {
                close(con);
            }
            return userList;
        }

    /**
     * Checks if a user is blacklisted
     * @param login users login
     * @return <code>true</code> - user in blacklist
     *         <p><code>false</code> - user not in blacklist</p>
     *
     * @throws DBException
     */
        public static boolean checkForBlock(String login) throws DBException {
            boolean result;
            ConnectionPool conPool = ConnectionPool.getInstance();
            log.debug("Obtaining connection");
            Connection con = conPool.getConnection();
            try {
                log.debug("Checking user for block");
                result = UserDAO.checkForBlock(con, login);
            } catch (DBException e) {
                log.error(e.getMessage());
                throw new DBException(e.getMessage(), e);
            } finally{
                close(con);
            }
            return result;
        }

        public static void blockUser(String login) throws DBException {
            ConnectionPool conPool = ConnectionPool.getInstance();
            log.debug("Obtaining connection");
            Connection con = conPool.getConnection();
            try {
                log.debug("Blocking user");
               UserDAO.blockUser(con,login);
            } catch (DBException e) {
                log.error(e.getMessage());
                throw new DBException(e.getMessage(), e);
            } finally{
                close(con);
            }
        }

        public static void unblockUser(String login) throws DBException {
            ConnectionPool conPool = ConnectionPool.getInstance();
            log.debug("Obtaining connection");
            Connection con = conPool.getConnection();
            try {
                log.debug("Unblocking user");
                UserDAO.unblockUser(con,login);
            } catch (DBException e) {
                log.error(e.getMessage());
                throw new DBException(e.getMessage(), e);
            } finally{
                close(con);
            }
        }

    }

