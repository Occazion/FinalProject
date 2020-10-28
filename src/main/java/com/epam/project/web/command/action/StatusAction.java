package com.epam.project.web.command.action;

import com.epam.project.db.Status;
import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatusAction extends Action {

    private static final Logger log = Logger.getLogger(StatusAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppException, DBException {
        log.debug("Action starts");

        String tourIdParam = request.getParameter("tourId");
        log.trace("Tour id  --> " + tourIdParam);
        long tourId;
        tourId = checkId(tourIdParam);
        int statusToChange = Integer.parseInt(request.getParameter("statusToChange"));
        log.trace("New status id  --> " + statusToChange);
        TourService.updateTourStatus(tourId, Status.getStatus(statusToChange));


        log.debug("Action finished");
    }
}
