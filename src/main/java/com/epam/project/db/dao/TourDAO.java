package com.epam.project.db.dao;

import com.epam.project.db.EntityMapper;
import com.epam.project.db.Fields;
import com.epam.project.db.Status;
import com.epam.project.db.entity.Tour;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
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
    private static final String SQL__FIND_ALL_OPENED_TOURS =
            "SELECT * FROM tours WHERE status = 0";
    private static final String SQL__INSERT_TOUR =
            "INSERT INTO tours values (default,?,?,?,?,?,?,?,?)";
            //"INSERT INTO tours(id,type,hotel,price,human_amount,isFire,status,discount,user_id) VALUE (?,?,?,?,?,?,?,?)";
    private static final String SQL__ORDER_TOUR =
            "UPDATE tours SET status = ?,user_id = ? WHERE id = ?";
    private static final String SQL__UPDATE_TOUR =
            "UPDATE tours SET type = ? ," +
                    "hotel = ? ," +
                    "price = ? ," +
                    "human_amount = ? ," +
                    "isFire = ? ," +
                    "status = ?," +
                    "discount = ? ," +
                    "user_id = ?  WHERE id = ?";
    private static final String SQL__UPDATE_TOUR_STATUS =
            "UPDATE tours SET status = ? WHERE id = ?";
    private static final String SQL__UPDATE_TOUR_DISCOUNT =
            "UPDATE tours SET discount = ? WHERE id = ?";
    private static final String SQL__UPDATE_TOUR_IS_FIRE =
            "UPDATE tours SET isFire = ? WHERE id = ?";
    private static final String SQL__DELETE_TOUR ="DELETE FROM tours WHERE id = ?";
    private static final String SQL__PAGINATION ="SELECT * FROM tours WHERE status = 0 LIMIT ? OFFSET ?";

    public static void insertTour(Connection con, Tour tour) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__INSERT_TOUR);

            stmt.setString(1,tour.getType());//Type
            stmt.setString(2,tour.getHotel());//Hotel
            stmt.setInt(3,tour.getPrice());//Price
            stmt.setInt(4,tour.getHuman_amount());//Human amount
            stmt.setBoolean(5,tour.getFire());//isFire
            stmt.setInt(6,tour.getStatusId());//status
            stmt.setInt(7,tour.getDiscount());//discount
            stmt.setNull(8,Types.INTEGER);//user_id

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
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
            throw new DBException(ex.getMessage(), ex);
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
            throw new DBException(e.getMessage(), e);
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
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(stmt, rs);
        }
        return result;
    }

    public static List<Tour> findAllToursForPage(Connection con,int limit,int pageNum) throws DBException {
        List<Tour> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int offset = (limit * pageNum) - limit;

        try {
            TourMapper mapper = new TourMapper();
            stmt = con.prepareStatement(SQL__PAGINATION);
            stmt.setInt(1,limit);
            stmt.setInt(2,offset);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(mapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(stmt, rs);
        }
        return result;
    }

    public static List<Tour> findAllOpenedTours(Connection con) throws DBException {
        List<Tour> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            TourMapper mapper = new TourMapper();
            stmt = con.prepareStatement(SQL__FIND_ALL_OPENED_TOURS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(mapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(stmt, rs);
        }
        return result;
    }

    public static void orderTour(Connection con, Long userId, int tourId, Status status) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__ORDER_TOUR);

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

    public static void updateTourStatus(Connection con, long tourId, Status status) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__UPDATE_TOUR_STATUS);

            stmt.setInt(1,status.ordinal());
            stmt.setLong(2,tourId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }
    }

    public static void updateTourDiscount(Connection con, int tourId, int discount) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__UPDATE_TOUR_DISCOUNT);

            stmt.setInt(1,discount);
            stmt.setInt(2,tourId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }
    }

    public static void updateTourFireStatus(Connection con, int tourId, boolean isFire) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__UPDATE_TOUR_IS_FIRE);

            stmt.setBoolean(1,isFire);
            stmt.setInt(2,tourId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }
    }

    public static void updateTour(Connection con, Tour tour) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__UPDATE_TOUR);

            stmt.setString(1,tour.getType());
            stmt.setString(2,tour.getHotel());
            stmt.setInt(3,tour.getPrice());
            stmt.setInt(4,tour.getHuman_amount());
            stmt.setBoolean(5,tour.getFire());
            stmt.setInt(6,tour.getStatusId());
            stmt.setInt(7,tour.getDiscount());
            stmt.setInt(8,tour.getUser_id());
            stmt.setLong(9,tour.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(stmt);
        }
    }

    public static void deleteTour(Connection con, int tourId) throws DBException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(SQL__DELETE_TOUR);

            stmt.setInt(1,tourId);

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
