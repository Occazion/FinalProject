package com.epam.project.db.dao;

import com.epam.project.db.Status;
import com.epam.project.db.entity.Tour;
import com.epam.project.exception.DBException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TourDAOTest {

    @AfterEach
    void clean() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from tours where hotel = 'test'");

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertTour() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(0);

        TourDAO.insertTour(con,tour);

        assertEquals(TourDAO.findTour(con, "test").getHotel(), tour.getHotel());
    }

    @Test
    void findAllToursByUserId() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        assertNotNull(TourDAO.findAllToursByUserId(con, 1L));
    }

    @Test
    void findAllTours() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        assertTrue(TourDAO.findAllTours(con).size() > 0);
    }

    @Test
    void findAllToursForPage() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        assertTrue(TourDAO.findAllToursForPage(con,1,1).size() > 0);
    }

    @Test
    void findAllOpenedTours() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        assertTrue(TourDAO.findAllOpenedTours(con).size() > 0);
    }

    @Test
    void orderTour() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        TourDAO.orderTour(con,100L,TourDAO.findTour(con, "test").getId(), Status.CONFIRMED);

        assertTrue(TourDAO.findTour(con,"test").getStatusId() > 0);
    }

    @Test
    void updateTourStatus() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        TourDAO.updateTourStatus(con,TourDAO.findTour(con,"test").getId(),Status.CONFIRMED);

        assertTrue(TourDAO.findTour(con,"test").getStatusId() > 0);
    }

    @Test
    void updateTourDiscount() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        TourDAO.updateTourDiscount(con, TourDAO.findTour(con, "test").getId(),10);

        assertTrue(TourDAO.findTour(con,"test").getDiscount() > 0);
    }

    @Test
    void updateTourFireStatus() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        TourDAO.updateTourFireStatus(con, TourDAO.findTour(con, "test").getId(),false);

        assertFalse(TourDAO.findTour(con,"test").getFire());
    }

    @Test
    void updateTour() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        tour = TourDAO.findTour(con, "test");

        tour.setType("test1");

        TourDAO.updateTour(con,tour);

        assertEquals(TourDAO.findTour(con,"test").getType(),tour.getType());
    }

    @Test
    void deleteTour() throws DBException {
        TestConnectionPool conPool = TestConnectionPool.getInstance();
        Connection con = conPool.getConnection();

        Tour tour = new Tour();
        tour.setId((long) 100);
        tour.setType("test");
        tour.setHotel("test");
        tour.setPrice(0);
        tour.setHumanAmount(0);
        tour.setFire(true);
        tour.setStatusId(0);
        tour.setDiscount(0);
        tour.setUserId(100);

        TourDAO.insertTour(con,tour);

        tour.setType("test1");

        TourDAO.deleteTour(con, TourDAO.findTour(con, "test").getId());

        assertNull(TourDAO.findTour(con,"test"));
    }
}