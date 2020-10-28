package com.epam.project.web;

import com.epam.project.Path;
import com.epam.project.exception.AppException;
import com.epam.project.web.command.Command;
import com.epam.project.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller extends HttpServlet {

    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private static final List<String> redirect = new ArrayList<>();

    @Override
    public void init() throws ServletException {

        redirect.add("signup");
        redirect.add("logout");

        super.init();
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        log.debug("Controller starts");

        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);

        Command command = CommandContainer.get(commandName);
        log.trace("Obtained command --> " + command);

        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }

        log.debug("Controller finished, now go to address --> " + forward);
        if (redirect.contains(commandName)) {
            log.trace("Redirect address --> " + forward);
            response.sendRedirect(forward);
        }
        else {
            log.trace("Forward address --> " + forward);
            request.getRequestDispatcher(forward).forward(request, response);
        }

    }

}
