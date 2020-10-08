package com.epam.project.web.command;

import com.epam.project.Path;
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


        log.debug("Command finished");
        return Path.COMMAND_USERS;
    }
}
