package com.epam.project.db.dao;

import com.epam.project.db.EntityMapper;
import com.epam.project.db.Fields;
import com.epam.project.db.entity.User;
import com.epam.project.exception.DBException;
import com.epam.project.web.command.CommandContainer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO{

    private static final Logger log = Logger.getLogger(UserDAO.class);

    private UserDAO() {

    }

    private static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login = ?";
    private static final String SQL__FIND_ALL_USERS =
            "SELECT * FROM users";
    private static final String SQL__INSERT_USER =
            "INSERT INTO users(login,password,role_id,locale) VALUE (?,?,?,?)";
    private static final String SQL__FIND_USER_IN_BLACKLIST =
            "SELECT * FROM blacklist WHERE login = ?";
    private static final String SQL__INSERT_USER_INTO_BLACKLIST =
            "INSERT INTO blacklist(login) value(?)";
    private static final String SQL__DELETE_USER_FROM_BLACKLIST =
            "DELETE FROM blacklist WHERE login = ?";

    public static void insertUser(Connection con, User user) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__INSERT_USER);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getRoleId());
            stmt.setString(4, user.getLocale());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException();
        }
        finally {
            close(stmt);
        }
    }

    public static User findUser(Connection con,String login) throws DBException {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            UserMapper mapper = new UserMapper();
            stmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage(), ex);
        } finally {
            close(stmt, rs);
        }
        return user;
    }

    public static List<User> findAllUsers(Connection con) throws DBException {
        List<User> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            UserMapper mapper = new UserMapper();
            stmt = con.prepareStatement(SQL__FIND_ALL_USERS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(mapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException();
        }
        finally {
            close(stmt, rs);
        }
        return result;
    }

    public static boolean checkForBlock(Connection con,String login) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            UserMapper mapper = new UserMapper();
            stmt = con.prepareStatement(SQL__FIND_USER_IN_BLACKLIST);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage(), ex);
        } finally {
            close(stmt, rs);
        }
        return false;
    }

    public static void blockUser(Connection con,String login) throws DBException {
        if (checkForBlock(con, login)) {
            return;
        }
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL__INSERT_USER_INTO_BLACKLIST);
            stmt.setString(1, login);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }

    }

    public static void unblockUser(Connection con,String login) throws DBException {
        if (!checkForBlock(con, login)) {
            return;
        }
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL__DELETE_USER_FROM_BLACKLIST);
            stmt.setString(1, login);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }
    }

    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getLong(Fields.ENTITY__ID));
                user.setLogin(rs.getString(Fields.USER__LOGIN));
                user.setPassword(rs.getString(Fields.USER__PASSWORD));
                user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));
                user.setLocale(rs.getString(Fields.USER__LOCALE));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
