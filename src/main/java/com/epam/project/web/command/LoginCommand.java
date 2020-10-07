package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.Role;
import com.epam.project.db.entity.User;
import com.epam.project.db.hashing.Hash;
import com.epam.project.db.hashing.HashAlgorithm;
import com.epam.project.db.service.UserService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LoginCommand extends Command {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        log.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
        log.trace("Request parameter: login --> " + login);

        String password = null;
        try {
            password = Hash.toHash(request.getParameter("password"), HashAlgorithm.SHA_256);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        User user = null;
        try {
            user = UserService.findUser(login);
        } catch (DBException e) {
            log.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN);
            throw new AppException();
        }
        log.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role userRole = Role.getRole(user);
            log.trace("userRole --> " + userRole);

            if (userRole == Role.ADMIN)
                forward = Path.COMMAND_TOUR_MENU;
//
//            if (userRole == Role.CLIENT)
//                forward = Path.COMMAND__LIST_MENU;

            session.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);

            session.setAttribute("userRole", userRole);
            log.trace("Set the session attribute: userRole --> " + userRole);

            log.info("User " + user + " logged as " + userRole.toString().toLowerCase());

            // work with i18n
            String userLocaleName = user.getLocale();
            log.trace("userLocalName --> " + userLocaleName);

            if (userLocaleName != null && !userLocaleName.isEmpty()) {
                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);

                session.setAttribute("defaultLocale", userLocaleName);
                log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);

                log.info("Locale for user: defaultLocale --> " + userLocaleName);
            }
        }
        log.debug("Command finished");
        return forward;
    }
}