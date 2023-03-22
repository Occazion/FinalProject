package com.epam.project.db.dao;

import com.epam.project.db.hashing.Hash;
import com.epam.project.db.hashing.HashAlgorithm;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;
import org.h2.tools.RunScript;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private final Logger log = Logger.getLogger(ConnectionPool.class);

    private SimpleDriverDataSource ds;

    private ConnectionPool() {
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MYSQL;"
                + "INIT=RUNSCRIPT FROM 'classpath:/sql/schema.sql'";

        ds = new SimpleDriverDataSource();
        ds.setDriver(new org.h2.Driver());
        ds.setUrl(url);
        log.trace("Data source ==> " + ds);
        try(Connection con = ds.getConnection();) {

            con.prepareStatement("INSERT INTO FINALPROJECT.STATUSES (ID, NAME) VALUES (0, 'OPENED')").execute();
            con.prepareStatement("INSERT INTO FINALPROJECT.STATUSES (ID, NAME) VALUES (1, 'CONFIRMED')").execute();
            con.prepareStatement("INSERT INTO FINALPROJECT.STATUSES (ID, NAME) VALUES (3, 'PAID')").execute();
            con.prepareStatement("INSERT INTO FINALPROJECT.STATUSES (ID, NAME) VALUES (4, 'CLOSED')").execute();

            con.prepareStatement("INSERT INTO FINALPROJECT.ROLES (ID, NAME) VALUES (0, 'ADMIN')").execute();
            con.prepareStatement("INSERT INTO FINALPROJECT.ROLES (ID, NAME) VALUES (1, 'MANAGER')").execute();
            con.prepareStatement("INSERT INTO FINALPROJECT.ROLES (ID, NAME) VALUES (2, 'CLIENT')").execute();

            con.prepareStatement("INSERT INTO FINALPROJECT.USERS (ID, LOGIN, PASSWORD, ROLE_ID, LOCALE) VALUES (1, 'admin', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', 0, 'en')").execute();
            con.prepareStatement("INSERT INTO FINALPROJECT.USERS_INFO (ID, NAME, SURNAME, GENDER, EMAIL, CITY) VALUES (1, 'Yehor', 'Y', '0', 'admin@email.com', 'Kharkiv')").execute();

            con.prepareStatement("INSERT INTO FINALPROJECT.USERS (ID, LOGIN, PASSWORD, ROLE_ID, LOCALE) VALUES (2, 'manager', '6EE4A469CD4E91053847F5D3FCB61DBCC91E8F0EF10BE7748DA4C4A1BA382D17', 1, 'en')").execute();
            con.prepareStatement("INSERT INTO FINALPROJECT.USERS_INFO (ID, NAME, SURNAME, GENDER, EMAIL, CITY) VALUES (2, 'Yehor', 'Y', '0', 'manager@email.com', 'Kharkiv')").execute();

            con.prepareStatement("INSERT INTO FINALPROJECT.USERS (ID, LOGIN, PASSWORD, ROLE_ID, LOCALE) VALUES (3, 'client', '948FE603F61DC036B5C596DC09FE3CE3F3D30DC90F024C85F3C82DB2CCAB679D', 2, 'en')").execute();
            con.prepareStatement("INSERT INTO FINALPROJECT.USERS_INFO (ID, NAME, SURNAME, GENDER, EMAIL, CITY) VALUES (3, 'Yehor', 'Y', '0', 'client@email.com', 'Kharkiv')").execute();

            con.prepareStatement("INSERT INTO FINALPROJECT.TOURS (ID, TYPE, HOTEL, PRICE, HUMAN_AMOUNT, ISFIRE, STATUS, DISCOUNT, USER_ID) VALUES (1, 'Test', 'Hotel test', 11500, 2, 1, 0, 10, null)").execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() throws DBException {
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() throws DBException {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            log.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
