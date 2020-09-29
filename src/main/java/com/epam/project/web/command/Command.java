package com.epam.project.web.command;

import com.epam.project.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {
    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException,
            AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
