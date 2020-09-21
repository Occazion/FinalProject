package com.epam.project.login;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String name = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            writer.println("<p>Name: " + name + "</p>");
            writer.println("<p>Age: " + password + "</p>");
        } finally {
            writer.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        try {
            writer.println("<p>Name: " + 13 + "</p>");
            writer.println("<p>Age: " + 13 + "</p>");
        } finally {
            writer.close();
        }
    }
}
