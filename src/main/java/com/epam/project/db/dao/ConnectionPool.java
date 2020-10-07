package com.epam.project.db.dao;

import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private final Logger log = Logger.getLogger(ConnectionPool.class);

    private DataSource ds;

    private ConnectionPool() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // ST4DB - the name of data source
            ds = (DataSource) envContext.lookup("jdbc/pool");
            log.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            log.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
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
