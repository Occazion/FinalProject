package com.epam.project.exception;

import java.sql.SQLException;

public class DBException extends SQLException {
    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
