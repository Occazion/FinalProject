package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.Status;
import com.epam.project.db.entity.Tour;
import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;
import sun.plugin.dom.core.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ManageToursCommand extends Command{
    private static final Logger log = Logger.getLogger(ManageToursCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        log.debug("Command starts");

        String action = request.getParameter("actionType");
        log.trace("Action --> " + action);
        String tourIdParam = request.getParameter("tourId");
        log.trace("Tour id  --> " + tourIdParam);
        int tourId;


        try {

            switch (action) {
                case "fire":
                    tourId = checkId(tourIdParam);
                    boolean isFire = Boolean.parseBoolean(request.getParameter("isFire"));
                    log.trace("New isFire  --> " + isFire);
                    TourService.updateTourFireStatus(tourId, isFire);
                    break;
                case "status":
                    tourId = checkId(tourIdParam);
                    int statusToChange = Integer.parseInt(request.getParameter("statusToChange"));
                    log.trace("New status id  --> " + statusToChange);
                    TourService.updateTourStatus(tourId, Status.getStatus(statusToChange));
                    break;
                case "discount":
                    tourId = checkId(tourIdParam);
                    int discountToSet = Integer.parseInt(request.getParameter("discountToSet"));
                    log.trace("New tour discount  --> " + discountToSet);
                    TourService.updateTourDiscount(tourId, discountToSet);
                    break;
                case "add": {
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
                    tour.setHuman_amount(tourAmount);

                    tour.setFire(false);

                    tour.setStatusId(0);

                    tour.setUser_id(0);

                    TourService.insertTour(tour);

                    break;
                }
                case "delete":
                    tourId = checkId(tourIdParam);
                    log.trace("Deleting tour with id  --> " + tourId);
                    TourService.deleteTour(tourId);
                    break;
                case "change": {
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
                        tour.setHuman_amount(tourAmount);
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
                        tour.setUser_id(tourUserId);
                    }

                    TourService.updateTour(tour);


                    break;
                }
                default:
                    String errorMessage = "Unknown action type.";
                    log.error("errorMessage --> " + errorMessage);
                    throw new AppException(errorMessage);
            }
        } catch (DBException | SQLException e) {
            String errorMessage = "DBException : " + e.getMessage();
            log.error("errorMessage --> " + errorMessage);
            throw new AppException(errorMessage, e);
        } catch (AppException e) {
            String errorMessage = Messages.ERR_TOUR_NOT_SELECTED;
            log.error("errorMessage --> " + errorMessage);
            throw new AppException(errorMessage, e);
        }

        log.debug("Command finished");
        return Path.COMMAND_TOURS;
    }

    private int checkId(String tourId) throws AppException {
        try {
            return Integer.parseInt(tourId);
        } catch (NumberFormatException e) {
            throw new AppException(Messages.ERR_TOUR_NOT_SELECTED,e);
        }
    }
}
