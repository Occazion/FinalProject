package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.Status;
import com.epam.project.db.entity.User;
import com.epam.project.db.service.TourService;
import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class MakeOrderCommand extends Command{

    private static final Logger log = Logger.getLogger(MakeOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        log.debug("Command starts");
        String[] tourIds = request.getParameterValues("tourId");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        log.info("User id --> " + user.getId());
        ArrayList<Integer> idList = new ArrayList<>();
        for(String str : tourIds) {
            idList.add(Integer.parseInt(str));
            log.debug("Added tour id -->" + str + " to order list");
        }

        for(Integer integer : idList) {
            try {
                TourService.orderTour(user.getId(),integer, Status.CONFIRMED);
            } catch (DBException e) {
                String errorMessage = Messages.ERR_CANNOT_UPDATE_TOUR;
                request.setAttribute("errorMessage", errorMessage.concat(" : " + e.getMessage()));
                log.error("errorMessage --> " + errorMessage);
            }

        }

        log.debug("Command finished");
        return Path.COMMAND_TOUR_MENU;
    }
}
