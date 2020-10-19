package com.epam.project.db.dao;

import com.epam.project.exception.DBException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    @Test
    void findAllAccounts() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        assertTrue(AccountDAO.findAllAccounts(con).size() > 0);
    }
}