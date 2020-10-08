package com.epam.project.db.dao;

import com.epam.project.db.EntityMapper;
import com.epam.project.db.Fields;
import com.epam.project.db.entity.User;
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

    private static final String SQL__FIND_USER_INFO_BY_ID =
            "SELECT * FROM users_info WHERE id = ?";
    private static final String SQL__FIND_ALL_USERS =
            "SELECT * FROM users";
    private static final String SQL__INSERT_USER_INFO =
            "INSERT INTO users_info(id,name,surname,gender,email,city) VALUE (?,?,?,?,?,?)";

    public static void insertUserInfo(Connection con, UserInfo userInfo) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__INSERT_USER_INFO);
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
            stmt = con.prepareStatement(SQL__FIND_USER_INFO_BY_ID);
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

    public static List<UserInfo> findAllUsersInfo(Connection con) throws DBException {
        List<UserInfo> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            UserInfoMapper mapper = new UserInfoMapper();
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
