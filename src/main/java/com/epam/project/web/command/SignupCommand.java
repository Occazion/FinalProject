package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.entity.User;
import com.epam.project.db.entity.UserInfo;
import com.epam.project.db.hashing.Hash;
import com.epam.project.db.hashing.HashAlgorithm;
import com.epam.project.db.service.AccountService;
import com.epam.project.db.service.UserService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignupCommand extends Command{
    private static final Logger log = Logger.getLogger(SignupCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        log.debug("Command starts");

        User user = new User();
        UserInfo userInfo = new UserInfo();

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;

        String str = request.getParameter("login");
        log.trace("Request parameter: login --> " + str);
        try {
            if (UserService.findUser(str) != null) {
                errorMessage = Messages.ERR_CANNOT_CREATE_USER +": User with that login already exists";
                request.setAttribute("errorMessage", errorMessage);
                log.error("errorMessage --> " + errorMessage);
                return forward;
            }
        } catch (DBException e) {
            throw new AppException(e.getMessage(), e);
        }
        user.setLogin(str);

        str = request.getParameter("password");
        try {
            user.setPassword(Hash.toHash(str, HashAlgorithm.SHA_256));
        } catch (NoSuchAlgorithmException e) {
            errorMessage = Messages.ERR_CANNOT_OBTAIN_HASHING_ALGORITHM;
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        str = request.getParameter("locale");
        log.trace("Request parameter: locale --> " + str);
        user.setLocale(str);

        //Client
        user.setRoleId(2);

        str = request.getParameter("name");
        log.trace("Request parameter: name --> " + str);
        userInfo.setName(str);

        str = request.getParameter("surname");
        log.trace("Request parameter: surname --> " + str);
        userInfo.setSurname(str);

        str = request.getParameter("gender");
        log.trace("Request parameter: gender --> " + str);
        userInfo.setGender(str);

        str = request.getParameter("email");
        log.trace("Request parameter: email --> " + str);
        userInfo.setEmail(str);

        str = request.getParameter("city");
        log.trace("Request parameter: city --> " + str);
        userInfo.setCity(str);

        try {
            AccountService.insertAccount(user,userInfo);
        } catch (DBException | SQLException e) {
            errorMessage = Messages.ERR_CANNOT_INSERT_ACCOUNT_INFO +":"+ e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        //response.sendRedirect();

        forward = Path.PAGE_LOGIN;

        log.debug("Command finished");
        return forward;
    }
}
