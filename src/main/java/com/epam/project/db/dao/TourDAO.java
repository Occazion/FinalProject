package com.epam.project.db.dao;

import com.epam.project.db.EntityMapper;
import com.epam.project.db.Fields;
import com.epam.project.db.Status;
import com.epam.project.db.entity.Tour;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDAO extends  DAO{

    private static final Logger log = Logger.getLogger(TourDAO.class);

    private TourDAO() {

    }

    private static final String SQL__FIND_TOUR_BY_ID =
            "SELECT * FROM tours WHERE id = ?";
    private static final String SQL__FIND_TOUR_BY_USER_ID =
            "SELECT * FROM tours WHERE user_id = ?";
    private static final String SQL__FIND_ALL_TOURS =
            "SELECT * FROM tours";
    private static final String SQL__INSERT_TOUR =
            "INSERT INTO tours(type,hotel,price,human_amount,isFire,status,discount,user_id) VALUE (?,?,?,?,?,?,?,?)";
    private static final String SQL__UPDATE_TOUR_STATUS =
            "UPDATE tours SET status = ?,user_id = ? WHERE id = ?";


    public static void insertTour(Connection con, Tour tour) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__INSERT_TOUR);

            //TODO complete insert func

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException();
        }
        finally {
            close(stmt);
        }
    }

    public static Tour findTour(Connection con, int id) throws DBException {
        Tour tour = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            TourMapper mapper = new TourMapper();
            stmt = con.prepareStatement(SQL__FIND_TOUR_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next())
                tour = mapper.mapRow(rs);
        } catch (SQLException ex) {
            throw new DBException();
        } finally {
            close(stmt, rs);
        }
        return tour;
    }

    public static List<Tour> findAllToursByUserId(Connection con,Long userId) throws DBException {
        List<Tour> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            TourMapper mapper = new TourMapper();
            stmt = con.prepareStatement(SQL__FIND_TOUR_BY_USER_ID);
            stmt.setLong(1,userId);
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

    public static List<Tour> findAllTours(Connection con) throws DBException {
        List<Tour> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            TourMapper mapper = new TourMapper();
            stmt = con.prepareStatement(SQL__FIND_ALL_TOURS);
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

    public static void updateTourStatus(Connection con,Long userId, int tourId, Status status) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__UPDATE_TOUR_STATUS);

            stmt.setInt(1,status.ordinal());
            stmt.setLong(2,userId);
            stmt.setInt(3,tourId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }
    }

    private static class TourMapper implements EntityMapper<Tour> {

        @Override
        public Tour mapRow(ResultSet rs) {
            try {
                Tour tour = new Tour();
                tour.setId(rs.getLong(Fields.ENTITY__ID));
                tour.setType(rs.getString(Fields.TOUR__TYPE));
                tour.setHotel(rs.getString(Fields.TOUR__HOTEL));
                tour.setPrice(rs.getInt(Fields.TOUR__PRICE));
                tour.setHuman_amount(rs.getInt(Fields.TOUR__HUMAN_AMOUNT));
                tour.setFire(rs.getBoolean(Fields.TOUR__IS_FIRE));
                tour.setStatusId(rs.getInt(Fields.TOUR__STATUS_ID));
                tour.setDiscount(rs.getInt(Fields.TOUR__DISCOUNT));
                tour.setUser_id(rs.getInt(Fields.TOUR__USER_ID));
                return tour;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
