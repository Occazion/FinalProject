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
            Connection con = conPool.getConnection();
            try{
                UserDAO.insertUser(con,user);
            } catch (DBException e) {
                throw new DBException();
            }
            finally {
                close(con);
            }
        }

        public static User findUser(String login) throws DBException {
            User user;
            ConnectionPool conPool = ConnectionPool.getInstance();
            Connection con = conPool.getConnection();
            try{
                user = UserDAO.findUser(con,login);
            } catch (DBException e) {
                throw new DBException();
            }
            finally {
                close(con);
            }
            return user;
        }

        public static List<User> findAllUsers() throws DBException {
            List<User> userList;
            ConnectionPool conPool = ConnectionPool.getInstance();
            Connection con = conPool.getConnection();
            try{
                userList = UserDAO.findAllUsers(con);
            } catch (DBException e) {
                throw new DBException();
            }
            finally {
                close(con);
            }
            return userList;
        }


    }

