package com.epam.project.db.service;

import com.epam.project.db.dao.ConnectionPool;
import com.epam.project.db.dao.UserInfoDAO;
import com.epam.project.db.entity.UserInfo;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class UserInfoService extends Service{

    private UserInfoService() {

    }

    private static final Logger log = Logger.getLogger(UserInfoService.class);

    /**
     * Inserts <code>UserInfo</code> to db
     * @param userInfo
     * @throws DBException
     * @see UserInfo
     */
    public static void insertUserInfo(UserInfo userInfo) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        UserInfoDAO.insertUserInfo(con,userInfo);
        try{
            log.debug("Inserting user info");
            UserInfoDAO.insertUserInfo(con,userInfo);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }

    }

    /**
     * Find UserInfo in DB by id
     * @param id UserInfo id
     * @return
     * @throws DBException
     * @see UserInfo
     */
    public static UserInfo findUserInfo(Long id) throws DBException {
        UserInfo userInfo;
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            log.debug("Finding user info");
            userInfo = UserInfoDAO.findUserInfo(con,id);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
        return userInfo;

    }

    /**
     * Find all UserInfo's in DB
     * @return List of UserInfo's
     * @throws DBException
     * @see UserInfo
     */
    public static List<UserInfo> findAllUsersInfo() throws DBException {
        List<UserInfo> userInfoList;
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            log.debug("Finding all users info");
            userInfoList = UserInfoDAO.findAllUsersInfo(con);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
        return userInfoList;
    }

    /**
     * Update a UserInfo
     * @param userInfo UserInfo to update
     * @throws
     * @see UserInfo
     */
    public static void updateUserInfo(UserInfo userInfo) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            log.debug("Updating user info");
            UserInfoDAO.updateUserInfo(con,userInfo);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }

    }
}
