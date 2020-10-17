package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.entity.Tour;
import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TourMenuCommand extends Command {

    private static final Logger log = Logger.getLogger(TourMenuCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        String page = request.getParameter("p");

        if (page == null || page.equals("")) {
            page = "1";
        }

        int pageNum = Integer.parseInt(page);

        if (pageNum < 1) {
            pageNum = 1;
        }

        // get menu items list
        List<Tour> tourList = null;
        try {
            //tourList = TourService.findAllOpenedTours();
            tourList = TourService.findAllToursForPage(2,pageNum);
        } catch (DBException e) {
            log.error(Messages.ERR_CANNOT_OBTAIN_ALL_TOURS);
        }
        log.trace("Found in DB: tour list --> " + tourList);

        log.debug("tour list size --> " + tourList.size());

        if (tourList.isEmpty()) {
            return Path.COMMAND_TOUR_MENU;
        }

        // sort menu by category
        tourList.sort((o1, o2) -> Boolean.compare(o1.getFire(), o2.getFire()));

        // put menu items list to the request
        request.setAttribute("tourList", tourList);
        log.trace("Set the request attribute: tourList --> " + tourList);

        request.setAttribute("p",pageNum);
        request.setAttribute("pageTitle","Tour menu");

        log.debug("Command finished");
        return Path.PAGE_TOUR_MENU;
    }
}
