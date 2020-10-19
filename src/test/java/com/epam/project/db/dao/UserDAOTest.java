package com.epam.project.db.dao;

import com.epam.project.db.entity.User;
import com.epam.project.exception.DBException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @AfterEach
    void setup() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from users where password = 'test'");

            stmt.executeUpdate();

            stmt = con.prepareStatement("delete from blacklist where login = 'test'");

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertUser() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        User user1 = null;

        User user2 = new User();
        user2.setLogin("test");
        user2.setPassword("test");
        user2.setRoleId(0);
        user2.setLocale("test");

        try {
            UserDAO.insertUser(con,user2);

            user1  = UserDAO.findUser(con, user2.getLogin());
        } catch (DBException e) {
            System.out.println(e.getMessage() + e);
        }
        assertEquals(user1.getPassword(),user2.getPassword());
        assertEquals(user1.getLocale(),user2.getLocale());

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from users where password = 'test'");

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAllUsers() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        User user1 = new User();
        user1.setLogin("test1");
        user1.setPassword("test");
        user1.setRoleId(0);
        user1.setLocale("test");

        User user2 = new User();
        user2.setLogin("test2");
        user2.setPassword("test");
        user2.setRoleId(0);
        user2.setLocale("test");

        List<User> userList = new ArrayList<>();
        try {
            UserDAO.insertUser(con,user1);
            UserDAO.insertUser(con,user2);

            userList  = UserDAO.findAllUsers(con);
        } catch (DBException e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(userList);
    }

    @Test
    void checkForBlock() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        assertFalse(UserDAO.checkForBlock(con,"test"));

    }

    @Test
    void blockUser() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        UserDAO.blockUser(con,"test");

        assertTrue(UserDAO.checkForBlock(con,"test"));
    }

    @Test
    void unblockUser() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        UserDAO.blockUser(con,"test");

        assertTrue(UserDAO.checkForBlock(con,"test"));

        UserDAO.unblockUser(con,"test");

        assertFalse(UserDAO.checkForBlock(con,"test"));
    }
}