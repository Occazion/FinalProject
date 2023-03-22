package com.epam.project.db.dao;

import com.epam.project.db.entity.User;
import com.epam.project.db.entity.UserInfo;
import com.epam.project.db.service.AccountService;
import com.epam.project.exception.DBException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    @Test
    void findAllAccounts() throws SQLException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        assertTrue(AccountDAO.findAllAccounts(con).size() == 0);
    }
}