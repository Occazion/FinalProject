package com.epam.project.web.command.action;

import com.epam.project.db.entity.Tour;
import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAction extends Action {

    private static final Logger log = Logger.getLogger(AddAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        log.debug("Action starts");

        Tour tour = new Tour();
        String tourType = request.getParameter("tourType");
        log.trace("New tour type  --> " + tourType);
        tour.setType(tourType);
        String tourHotel = request.getParameter("tourHotel");
        log.trace("New tour hotel  --> " + tourHotel);
        tour.setHotel(tourHotel);
        int tourPrice = Integer.parseInt(request.getParameter("tourPrice"));
        log.trace("New tour price  --> " + tourPrice);
        tour.setPrice(tourPrice);
        int tourAmount = Integer.parseInt(request.getParameter("tourAmount"));
        log.trace("New tour human amount  --> " + tourAmount);
        tour.setHumanAmount(tourAmount);

        tour.setFire(false);

        tour.setStatusId(0);

        tour.setUserId(0);

        TourService.insertTour(tour);

        log.debug("Action finished");
    }
}
