package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.entity.Tour;
import com.epam.project.db.entity.User;
import com.epam.project.db.service.TourService;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Tour> tourList = null;
        try {
            tourList = TourService.findAllToursByUserId(user.getId());
        } catch (DBException e) {
            log.error(Messages.ERR_CANNOT_OBTAIN_ALL_TOURS);
        }
        log.trace("Found in DB: tour list --> " + tourList);

        // sort menu by category
        tourList.sort((o1, o2) -> Boolean.compare(o1.getFire(), o2.getFire()));

        // put menu items list to the request
        request.setAttribute("tourList", tourList);
        log.trace("Set the request attribute: tourList --> " + tourList);

        log.debug("Command finished");
        return Path.PAGE_CABINET;
    }
}
