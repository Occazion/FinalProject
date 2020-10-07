package com.epam.project.exception;

public class DBException extends Exception {
    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
