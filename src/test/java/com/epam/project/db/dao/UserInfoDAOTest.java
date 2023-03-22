package com.epam.project.db.dao;

import com.epam.project.db.entity.User;
import com.epam.project.db.entity.UserInfo;
import com.epam.project.exception.DBException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoDAOTest {

    @AfterEach
    void clean() throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("delete from users where login = 'test'");

            stmt.executeUpdate();

            stmt = con.prepareStatement("delete from users_info where name = 'test'");

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertUserInfo() throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setRoleId(0);
        user.setLocale("test");

        UserDAO.insertUser(con,user);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(UserDAO.findUser(con,user.getLogin()).getId());
        userInfo.setName("test");
        userInfo.setSurname("test");
        userInfo.setGender("test");
        userInfo.setEmail("test");
        userInfo.setCity("test");

        UserInfoDAO.insertUserInfo(con,userInfo);

        assertEquals(UserInfoDAO.findUserInfo(con, "test").getSurname(), userInfo.getSurname());
    }

    @Test
    void findUserInfo() throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setRoleId(0);
        user.setLocale("test");

        UserDAO.insertUser(con,user);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(UserDAO.findUser(con,user.getLogin()).getId());
        userInfo.setName("test");
        userInfo.setSurname("test");
        userInfo.setGender("test");
        userInfo.setEmail("test");
        userInfo.setCity("test");

        UserInfoDAO.insertUserInfo(con,userInfo);


        assertEquals(UserInfoDAO.findUserInfo(con,UserInfoDAO.findUserInfo(con, "test").getId()).getSurname(), userInfo.getSurname());
    }

    @Test
    void findAllUsersInfo() throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        assertTrue(UserInfoDAO.findAllUsersInfo(con).size() > 0);
    }

    @Test
    void updateUserInfo() throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setRoleId(0);
        user.setLocale("test");

        UserDAO.insertUser(con,user);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(UserDAO.findUser(con,user.getLogin()).getId());
        userInfo.setName("test");
        userInfo.setSurname("test");
        userInfo.setGender("test");
        userInfo.setEmail("test");
        userInfo.setCity("test");

        UserInfoDAO.insertUserInfo(con,userInfo);
        userInfo = UserInfoDAO.findUserInfo(con, userInfo.getName());

        userInfo.setEmail("test1");
        UserInfoDAO.updateUserInfo(con,userInfo);
        assertEquals(UserInfoDAO.findUserInfo(con, userInfo.getName()).getEmail(),userInfo.getEmail());
    }
}