package com.epam.project.db.service;

import com.epam.project.db.Status;
import com.epam.project.db.dao.ConnectionPool;
import com.epam.project.db.dao.TourDAO;
import com.epam.project.db.entity.Tour;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TourService extends Service {

    private TourService(){}

    private static final Logger log = Logger.getLogger(TourService.class);

    public static void insertTour(Tour tour) throws DBException, SQLException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try {
            con.setAutoCommit(false);
            TourDAO.insertTour(con, tour);
            con.commit();
        } catch (DBException | SQLException e) {
            con.rollback();
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
    }

    public static Tour findTour(int id) throws DBException {
        Tour tour;
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
        tour = TourDAO.findTour(con,id);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
        return tour;
    }

    public static List<Tour> findAllTours() throws DBException {
        List<Tour> tours;
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            tours = TourDAO.findAllTours(con);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
        return tours;
    }

    public static List<Tour> findAllToursForPage(int limit,int pageNum) throws DBException {
        List<Tour> tours;
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            tours = TourDAO.findAllToursForPage(con,limit,pageNum);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
        return tours;
    }

    public static List<Tour> findAllOpenedTours() throws DBException {
        List<Tour> tours;
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            tours = TourDAO.findAllOpenedTours(con);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
        return tours;
    }

    public static List<Tour> findAllToursByUserId(Long userId) throws DBException {
        List<Tour> tours;
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            tours = TourDAO.findAllToursByUserId(con,userId);
        } catch (DBException e) {
            throw new DBException(e.getMessage(),e);
        }
        finally {
            close(con);
        }
        return tours;
    }

    /**
     *
     * @param userId - users id (who makes an order)
     * @param tourID - tour id (which is ordered)
     * @param status - status to set for tour
     * @throws DBException
     */
    public static void orderTour(Long userId, int tourID, Status status) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            TourDAO.orderTour(con,userId,tourID,status);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
    }

    public static void updateTourStatus(long tourID, Status status) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            TourDAO.updateTourStatus(con,tourID,status);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
    }

    public static void updateTourDiscount(int tourID, int discount) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            TourDAO.updateTourDiscount(con,tourID,discount);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
    }

    public static void updateTourFireStatus(int tourID, boolean isFire) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            TourDAO.updateTourFireStatus(con,tourID,isFire);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
    }

    public static void updateTour(Tour tour) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            TourDAO.updateTour(con,tour);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
    }

    public static void deleteTour(int tourId) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        log.debug("Obtaining connection");
        Connection con = conPool.getConnection();
        try{
            TourDAO.deleteTour(con,tourId);
        } catch (DBException e) {
            throw new DBException(e.getMessage(), e);
        }
        finally {
            close(con);
        }
    }

}
