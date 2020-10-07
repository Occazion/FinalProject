package com.epam.project.db.service;

import com.epam.project.db.dao.ConnectionPool;
import com.epam.project.db.dao.TourDAO;
import com.epam.project.db.entity.Tour;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TourService extends Service {

    private TourService(){}

    private static final Logger log = Logger.getLogger(TourService.class);

    public static void insertTour(Tour tour) throws DBException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        try {
            TourDAO.insertTour(con, tour);
        } catch (DBException e) {
            throw new DBException();
        }
        finally {
            close(con);
        }
    }

    public static Tour findTour(int id) throws DBException {
        Tour tour = new Tour();
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        try{
        tour = TourDAO.findTour(con,id);
        } catch (DBException e) {
            throw new DBException();
        }
        finally {
            close(con);
        }
        return tour;
    }

    public static List<Tour> findAllTours() throws DBException {
        List<Tour> tours;
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        try{
            tours = TourDAO.findAllTours(con);
        } catch (DBException e) {
            throw new DBException();
        }
        finally {
            close(con);
        }
        return tours;
    }
}
