package com.epam.project.web.command.action;

import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FireAction extends Action {

    private static final Logger log = Logger.getLogger(FireAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, DBException {
        log.debug("Action starts");

        String tourIdParam = request.getParameter("tourId");
        log.trace("Tour id  --> " + tourIdParam);
        Long tourId;
        tourId = checkId(tourIdParam);
        boolean isFire = Boolean.parseBoolean(request.getParameter("isFire"));
        log.trace("New isFire  --> " + isFire);
        TourService.updateTourFireStatus(tourId, isFire);

        log.debug("Action finished");
    }
}
