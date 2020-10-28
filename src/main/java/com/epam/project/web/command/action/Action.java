package com.epam.project.web.command.action;

import com.epam.project.exception.AppException;
import com.epam.project.exception.DBException;
import com.epam.project.exception.Messages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract class Action implements Serializable {
    /**
     * Execution method for action.
     *
     *
     */
    public abstract void execute(HttpServletRequest request,
                                   HttpServletResponse response) throws DBException,
            AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

    public final Long checkId(String tourId) throws AppException {
        try {
            return Long.parseLong(tourId);
        } catch (NumberFormatException e) {
            throw new AppException(Messages.ERR_TOUR_NOT_SELECTED,e);
        }
    }
}
