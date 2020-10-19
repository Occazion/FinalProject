package com.epam.project.db.dao;

import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class TestConnectionPool {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finalproject?serverTimezone=UTC";
    private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/finalproject?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "ro20mysqlpass20ot";

    private static TestConnectionPool instance = null;

    private TestConnectionPool() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static TestConnectionPool getInstance() {
        if (instance==null)
            instance = new TestConnectionPool();
        return instance;
    }

    public Connection getConnection() throws DBException {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
    }
}