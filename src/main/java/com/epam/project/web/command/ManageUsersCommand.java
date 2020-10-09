package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.service.UserService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class ManageUsersCommand extends Command{
    private static final Logger log = Logger.getLogger(ManageUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");
        String action = request.getParameter("actionType");
        log.trace("Action --> " + action);
        String[] logins = request.getParameterValues("login");
        log.trace(Arrays.toString(logins));

        try {

            if (action.equals("block")) {
                for (String login : logins) {
                    UserService.blockUser(login);
                }
            } else if (action.equals("unblock")) {
                for (String login : logins) {
                    UserService.unblockUser(login);
                }
            } else {
                String errorMessage = "Unknown action type.";
                request.setAttribute("errorMessage", errorMessage);
                log.error("errorMessage --> " + errorMessage);
                return Path.PAGE_ERROR_PAGE;
            }
        } catch (DBException e) {
            String errorMessage = "DBException : " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE_ERROR_PAGE;
        }
        log.debug("Command finished");
        return Path.COMMAND_USERS;
    }
}
