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
        URL scriptFile = ConnectionPool.class.getClassLoader().getResource("/sql/data.sql");
        try {
            RunScript.execute(ds.getConnection(), new FileReader(scriptFile.getFile()));
        } catch (SQLException | FileNotFoundException e) {

        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(Hash.toHash("admin", HashAlgorithm.SHA_256));
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
