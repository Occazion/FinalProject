package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.bean.AccountBean;
import com.epam.project.db.entity.Tour;
import com.epam.project.db.entity.User;
import com.epam.project.db.service.AccountService;
import com.epam.project.db.service.TourService;
import com.epam.project.db.service.UserService;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User panel
 */
public class UsersCommand extends Command {
    private static final Logger log = Logger.getLogger(UsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");

        List<AccountBean> accountBeans = null;
        try {
            accountBeans = AccountService.findAllAccounts();
        } catch (DBException e) {
            log.error(Messages.ERR_CANNOT_OBTAIN_ALL_TOURS);
        }
        log.trace("Found in DB: accountBeans --> " + accountBeans);

        // put menu items list to the request
        request.setAttribute("accountBeans", accountBeans);
        log.trace("Set the request attribute: accountBeans --> " + accountBeans);

        request.setAttribute("pageTitle","User manager");

        log.debug("Command finished");
        return Path.PAGE_USERS;
    }
}
