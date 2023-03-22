package com.epam.project.db.dao;

import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

class TestConnectionPool {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finalproject?serverTimezone=UTC";
    private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/finalproject?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "ro20mysqlpass20ot";

    private static TestConnectionPool instance = null;
    static SimpleDriverDataSource dataSource;

    static {

        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MYSQL;"
                    + "INIT=RUNSCRIPT FROM 'classpath:/sql/schema.sql'\\;RUNSCRIPT FROM 'classpath:/sql/data.sql'";

            dataSource = new SimpleDriverDataSource();
            dataSource.setDriver(new org.h2.Driver());
            dataSource.setUrl(url);

    }
    private TestConnectionPool() {

    }

    public static TestConnectionPool getInstance() {

        if (instance==null)
            instance = new TestConnectionPool();
        return instance;
    }

    public Connection getConnection() throws DBException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
    }
}