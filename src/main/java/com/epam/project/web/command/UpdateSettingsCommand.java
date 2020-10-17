package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.entity.User;
import com.epam.project.db.entity.UserInfo;
import com.epam.project.db.service.UserInfoService;
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

public class UpdateSettingsCommand extends Command{

    private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        log.debug("Command starts");

        // UPDATE USER ////////////////////////////////////////////////////////

        User user = (User)request.getSession().getAttribute("user");
        log.debug("Getting user from session -> " + user);
        UserInfo userInfo;
        boolean updateUser = false;
        try {
            userInfo = UserInfoService.findUserInfo(user.getId());
            log.debug("Getting user info from db -> " + userInfo);
        } catch (DBException e) {
            log.error(Messages.ERR_CANNOT_OBTAIN_USER_INFO_BY_ID + ":" + e);
            throw new AppException(e.getMessage(), e);
        }


        // update first name
        String name = request.getParameter("name");
        log.debug("Getting request param NAME -> " + name);
        if (name != null && !name.isEmpty()) {
            userInfo.setName(name);
            updateUser = true;
        }

        // update last name
        String surname = request.getParameter("surname");
        log.debug("Getting request param SURNAME -> " + surname);
        if (surname != null && !surname.isEmpty()) {
            userInfo.setSurname(surname);
            updateUser = true;
        }

        String localeToSet = request.getParameter("localeToSet");
        log.debug("Getting request param LOCALE TO SET -> " + localeToSet);
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
            updateUser = true;
        }

        HttpSession session = request.getSession();

        if (updateUser) {
            try {
                log.debug("Updating user info -> " + userInfo);
                UserInfoService.updateUserInfo(userInfo);
                session.setAttribute("userInfo", userInfo);
                log.trace("Set the session attribute: userInfo --> " + userInfo);
            } catch (DBException e) {
                log.error(Messages.ERR_CANNOT_UPDATE_USER_INFO + ":" + e);
                throw new AppException(e.getMessage(),e);
            }
        }

        log.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }
}
