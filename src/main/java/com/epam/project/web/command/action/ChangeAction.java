package com.epam.project.web.command.action;

import com.epam.project.db.entity.Tour;
import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeAction extends Action {

    private static final Logger log = Logger.getLogger(ChangeAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, DBException {
        log.debug("Action starts");

        String tourIdParam = request.getParameter("tourId");
        log.trace("Tour id  --> " + tourIdParam);
        Long tourId;
        tourId = checkId(tourIdParam);
        Tour tour = TourService.findTour(tourId);

        String tourType = request.getParameter("tourTypeUpd");
        if (!tourType.equals("")) {
            log.trace("New tour type  --> " + tourType);
            tour.setType(tourType);
        }
        String tourHotel = request.getParameter("tourHotelUpd");
        if (!tourHotel.equals("")) {
            log.trace("New tour hotel  --> " + tourHotel);
            tour.setHotel(tourHotel);
        }
        String str = request.getParameter("tourPriceUpd");
        if (!str.equals("")) {
            int tourPrice = Integer.parseInt(str);
            log.trace("New tour price  --> " + tourPrice);
            tour.setPrice(tourPrice);
        }
        str = request.getParameter("tourAmountUpd");
        if (!str.equals("")) {
            int tourAmount = Integer.parseInt(str);
            log.trace("New tour human amount  --> " + tourAmount);
            tour.setHumanAmount(tourAmount);
        }
        str = request.getParameter("tourIsFireUpd");
        if (!str.equals("")) {
            boolean tourIsFire = Boolean.parseBoolean(str);
            log.trace("New tour fire status  --> " + tourIsFire);
            tour.setFire(tourIsFire);
        }
        str = request.getParameter("tourDiscountUpd");
        if (!str.equals("")) {
            int tourDiscount = Integer.parseInt(str);
            log.trace("New tour human amount  --> " + tourDiscount);
            tour.setDiscount(tourDiscount);
        }
        str = request.getParameter("tourUserIdUpd");
        if (!str.equals("")) {
            int tourUserId = Integer.parseInt(str);
            log.trace("New tour user id  --> " + tourUserId);
            tour.setUserId(tourUserId);
        }

        TourService.updateTour(tour);

        log.debug("Action finished");
    }
}
