package com.epam.project.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {

    public static void close(PreparedStatement stmt, ResultSet rs) {
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    public static void close(PreparedStatement stmt) {
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

}
