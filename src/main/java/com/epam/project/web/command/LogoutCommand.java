package com.epam.project.web.command;

import com.epam.project.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command{
        private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            LOG.debug("Command starts");

            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            LOG.debug("Command finished");
            return Path.PAGE_LOGIN;
        }
}
