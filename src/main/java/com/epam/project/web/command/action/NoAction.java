package com.epam.project.web.command.action;

import com.epam.project.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoAction extends Action {

    private static final Logger log = Logger.getLogger(NoAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  AppException {
        log.debug("Action starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        log.error("Set the request attribute: errorMessage --> " + errorMessage);

        log.debug("Action finished");
    }
}
