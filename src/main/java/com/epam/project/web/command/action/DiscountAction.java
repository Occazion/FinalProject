package com.epam.project.web.command.action;

import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiscountAction extends Action {

    private static final Logger log = Logger.getLogger(DiscountAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, DBException {
        log.debug("Action starts");

        String tourIdParam = request.getParameter("tourId");
        log.trace("Tour id  --> " + tourIdParam);
        Long tourId;
        tourId = checkId(tourIdParam);
        int discountToSet = Integer.parseInt(request.getParameter("discountToSet"));
        log.trace("New tour discount  --> " + discountToSet);
        TourService.updateTourDiscount(tourId, discountToSet);

        log.debug("Action finished");
    }
}
