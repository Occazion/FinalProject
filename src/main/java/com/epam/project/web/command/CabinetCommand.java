package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.entity.Tour;
import com.epam.project.db.entity.User;
import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CabinetCommand extends Command {

    private static final Logger log = Logger.getLogger(CabinetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        log.debug("Command starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        log.debug("Get user from session -> " + user);

        List<Tour> tourList = null;
        try {
            tourList = TourService.findAllToursByUserId(user.getId());
            log.debug("Find tours by user id -> " + tourList);
        } catch (DBException e) {
            log.error(Messages.ERR_CANNOT_OBTAIN_ALL_TOURS + ":" + e);
            throw new AppException(e.getMessage(), e);
        }
        log.trace("Found in DB: tour list --> " + tourList);

        // sort menu by category
        log.debug("Sorting tour list by fire attribute");
        tourList.sort((o1, o2) -> Boolean.compare(o1.getFire(), o2.getFire()));

        // put items list to the request
        if(!tourList.isEmpty()) {
            request.setAttribute("tourList", tourList);
            log.trace("Set the request attribute: tourList --> " + tourList);
        }

        request.setAttribute("pageTitle","Cabinet");

        log.debug("Command finished");
        return Path.PAGE_CABINET;
    }
}
