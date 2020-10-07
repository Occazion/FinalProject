package com.epam.project.db.service;

import com.epam.project.db.dao.ConnectionPool;
import com.epam.project.db.dao.TourDAO;
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

    public static void insertUserInfo(UserInfo userInfo) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        UserInfoDAO.insertUserInfo(con,userInfo);
        try{
            UserInfoDAO.insertUserInfo(con,userInfo);
        } catch (DBException e) {
            throw new DBException();
        }
        finally {
            close(con);
        }

    }

    public static UserInfo findUserInfo(int id) throws DBException {
        UserInfo userInfo = new UserInfo();
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        try{
            userInfo = UserInfoDAO.findUserInfo(con,id);
        } catch (DBException e) {
            throw new DBException();
        }
        finally {
            close(con);
        }
        return userInfo;

    }

    public static List<UserInfo> findAllUsersInfo() throws DBException {
        List<UserInfo> userInfoList;
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        try{
            userInfoList = UserInfoDAO.findAllUsersInfo(con);
        } catch (DBException e) {
            throw new DBException();
        }
        finally {
            close(con);
        }
        return userInfoList;
    }
}
