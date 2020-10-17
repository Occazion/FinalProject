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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        log.debug("Command starts");
        String action = request.getParameter("actionType");
        log.trace("Action --> " + action);
        String[] logins = request.getParameterValues("login");
        log.trace(Arrays.toString(logins));

        try {

            if (action.equals("block")) {
                log.debug("Blocking users");
                for (String login : logins) {
                    UserService.blockUser(login);
                }
            } else if (action.equals("unblock")) {
                log.debug("Unblocking users");
                for (String login : logins) {
                    UserService.unblockUser(login);
                }
            } else {
                String errorMessage = "Unknown action type.";
                log.error("errorMessage --> " + errorMessage);
                throw new AppException(errorMessage);
            }
        } catch (DBException e) {
            String errorMessage = "DBException : " + e.getMessage();
            log.error("errorMessage --> " + errorMessage);
            throw new AppException(errorMessage, e);
        } catch (AppException e) {
            String errorMessage = "AppException : " + e.getMessage();
            log.error("errorMessage --> " + errorMessage);
            throw new AppException(errorMessage, e);
        }
        log.debug("Command finished");
        return Path.COMMAND_USERS;
    }
}
