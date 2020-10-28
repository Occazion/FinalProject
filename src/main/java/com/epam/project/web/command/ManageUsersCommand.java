package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.service.UserService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class ManageUsersCommand extends Command{
    private static final Logger log = Logger.getLogger(ManageUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        log.debug("Command starts");
        String action = request.getParameter("actionType");
        log.trace("Action --> " + action);
        List<String> logins = Arrays.asList(request.getParameterValues("login"));
        log.trace(logins);

        try {

            if (action.equals("block")) {
                log.debug("Blocking users");
                logins.forEach(login -> {
                    try {
                        UserService.blockUser(login);
                    } catch (DBException e) {
                        String errorMessage = "DBException : " + e.getMessage();
                        log.error("errorMessage --> " + errorMessage);
                    }
                });
            } else if (action.equals("unblock")) {
                log.debug("Unblocking users");
                logins.forEach(login -> {
                    try {
                        UserService.unblockUser(login);
                    } catch (DBException e) {
                        String errorMessage = "DBException : " + e.getMessage();
                        log.error("errorMessage --> " + errorMessage);
                    }
                });
            } else {
                String errorMessage = "Unknown action type.";
                log.error("errorMessage --> " + errorMessage);
                throw new AppException(errorMessage);
            }
        } catch (AppException e) {
            String errorMessage = "AppException : " + e.getMessage();
            log.error("errorMessage --> " + errorMessage);
            throw new AppException(errorMessage, e);
        }
        log.debug("Command finished");
        return Path.COMMAND_USERS;
    }
}
