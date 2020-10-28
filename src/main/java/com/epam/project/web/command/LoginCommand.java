package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.Role;
import com.epam.project.db.entity.User;
import com.epam.project.db.entity.UserInfo;
import com.epam.project.db.hashing.Hash;
import com.epam.project.db.hashing.HashAlgorithm;
import com.epam.project.db.service.UserInfoService;
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

        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;

        String login = request.getParameter("login");
        log.trace("Request parameter: login --> " + login);

        try {
            if (UserService.checkForBlock(login)) {
                errorMessage = Messages.ERR_CANNOT_LOGIN_USER_IS_BLOCKED;
                request.setAttribute("errorMessage", errorMessage);
                log.error("errorMessage --> " + errorMessage);
                return forward;
            }
        } catch (DBException e) {
            errorMessage = Messages.ERR_CANNOT_CHECK_USER_FOR_BLOCK;
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage.concat(" : " + e.getMessage()));
            return forward;
        }

        String password = null;
        try {
            password = Hash.toHash(request.getParameter("password"), HashAlgorithm.SHA_256);
        } catch (NoSuchAlgorithmException e) {
            errorMessage = Messages.ERR_CANNOT_OBTAIN_HASHING_ALGORITHM;
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }


        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        User user = null;
        UserInfo userInfo = null;
        try {
            user = UserService.findUser(login);
            userInfo = UserInfoService.findUserInfo(user.getId());

        } catch (DBException e) {
            errorMessage = Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN;
            request.setAttribute("errorMessage", errorMessage);
            log.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN);
        }
        log.trace("Found in DB: user --> " + user);
        log.trace("Found in DB: userInfo --> " + userInfo);

        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role userRole = Role.getRole(user);
            log.trace("userRole --> " + userRole);

            forward = Path.COMMAND_TOUR_MENU;

            session.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);

            session.setAttribute("userRole", userRole);
            log.trace("Set the session attribute: userRole --> " + userRole);

            session.setAttribute("userInfo", userInfo);
            log.trace("Set the session attribute: userInfo --> " + userInfo);

            log.info("User " + user + " logged as " + userRole.toString().toLowerCase());

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