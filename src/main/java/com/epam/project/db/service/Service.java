package com.epam.project.db.service;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Service {
    public static void close(Connection con) {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}
