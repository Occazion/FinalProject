package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.Status;
import com.epam.project.db.entity.Tour;
import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import com.epam.project.web.command.action.Action;
import com.epam.project.web.command.action.ActionContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ManageToursCommand extends Command{
    private static final Logger log = Logger.getLogger(ManageToursCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        log.debug("Command starts");

        String actionName = request.getParameter("actionType");
        log.trace("Action --> " + actionName);

        Action action = ActionContainer.get(actionName);
        log.trace("Obtained action --> " + action);

        try {
            action.execute(request, response);
        } catch (DBException e) {
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

}
