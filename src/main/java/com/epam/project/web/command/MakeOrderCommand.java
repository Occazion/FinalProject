package com.epam.project.web.command;

import com.epam.project.Path;
import com.epam.project.db.entity.User;
import com.epam.project.exception.AppException;
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

        log.debug("Command finished");
        return Path.COMMAND_TOUR_MENU;
    }
}
