package com.epam.project.db.dao;

import com.epam.project.db.EntityMapper;
import com.epam.project.db.Fields;
import com.epam.project.db.entity.UserInfo;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAO extends DAO{
    private static final Logger log = Logger.getLogger(UserInfoDAO.class);

    private UserInfoDAO() {

    }

    private static final String SQL_FIND_USER_INFO_BY_ID =
            "SELECT * FROM users_info WHERE id = ?";
    private static final String SQL_FIND_USER_INFO_BY_NAME =
            "SELECT * FROM users_info WHERE name = ?";
    private static final String SQL_FIND_ALL_USERS_INFO =
            "SELECT * FROM users_info";
    private static final String SQL_INSERT_USER_INFO =
            "INSERT INTO users_info(id,name,surname,gender,email,city) VALUE (?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USER_INFO =
            "UPDATE users_info SET name = ? ," +
                    "surname = ? ," +
                    "gender = ? ," +
                    "email = ? ," +
                    "city = ? WHERE id = ?";

    public static void insertUserInfo(Connection con, UserInfo userInfo) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL_INSERT_USER_INFO);
            stmt.setLong(1,  userInfo.getId());
            stmt.setString(2, userInfo.getName());
            stmt.setString(3, userInfo.getSurname());
            stmt.setString(4, userInfo.getGender());
            stmt.setString(5, userInfo.getEmail());
            stmt.setString(6, userInfo.getCity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }
    }

    public static UserInfo findUserInfo(Connection con,Long id) throws DBException {
        UserInfo userInfo = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            UserInfoMapper mapper = new UserInfoMapper();
            stmt = con.prepareStatement(SQL_FIND_USER_INFO_BY_ID);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next())
                userInfo = mapper.mapRow(rs);
        } catch (SQLException ex) {
            throw new DBException();
        } finally {
            close(stmt, rs);
        }
        return userInfo;
    }

    public static UserInfo findUserInfo(Connection con,String name) throws DBException {
        UserInfo userInfo = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            UserInfoMapper mapper = new UserInfoMapper();
            stmt = con.prepareStatement(SQL_FIND_USER_INFO_BY_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if (rs.next())
                userInfo = mapper.mapRow(rs);
        } catch (SQLException ex) {
            throw new DBException();
        } finally {
            close(stmt, rs);
        }
        return userInfo;
    }

    public static List<UserInfo> findAllUsersInfo(Connection con) throws DBException {
        List<UserInfo> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            UserInfoMapper mapper = new UserInfoMapper();
            stmt = con.prepareStatement(SQL_FIND_ALL_USERS_INFO);
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

    public static void updateUserInfo(Connection con, UserInfo userInfo) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL_UPDATE_USER_INFO);

            stmt.setString(1,userInfo.getName());
            stmt.setString(2,userInfo.getSurname());
            stmt.setString(3,userInfo.getGender());
            stmt.setString(4,userInfo.getEmail());
            stmt.setString(5,userInfo.getCity());
            stmt.setLong(6,userInfo.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }
    }

    private static class UserInfoMapper implements EntityMapper<UserInfo> {

        @Override
        public UserInfo mapRow(ResultSet rs) {
            try {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(rs.getLong(Fields.ENTITY__ID));
                userInfo.setName(rs.getString(Fields.USERS_INFO__NAME));
                userInfo.setSurname(rs.getString(Fields.USERS_INFO__SURNAME));
                userInfo.setGender(rs.getString(Fields.USERS_INFO__GENDER));
                userInfo.setEmail(rs.getString(Fields.USERS_INFO__EMAIL));
                userInfo.setCity(rs.getString(Fields.USERS_INFO__CITY));
                return userInfo;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
