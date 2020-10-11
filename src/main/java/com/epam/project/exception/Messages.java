package com.epam.project.exception;

public class Messages {
    private Messages() {
        // no op
    }

    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

    public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";

    public static final String ERR_CANNOT_CREATE_USER = "Cannot create a user";

    public static final String ERR_CANNOT_LOGIN_USER_IS_BLOCKED = "Cannot login: User is blocked. Contact administrator for details";

    public static final String ERR_CANNOT_CHECK_USER_FOR_BLOCK = "Cannot check user in blacklist";

    public static final String ERR_CANNOT_UPDATE_TOUR = "Cannot update tour";

    public static final String ERR_CANNOT_OBTAIN_ALL_TOURS = "Cannot obtain all tours";

    public static final String ERR_CANNOT_OBTAIN_ALL_OPENED_TOURS = "Cannot obtain all opened tours";

    public static final String ERR_TOUR_NOT_SELECTED = "Tour not selected";

    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

    public static final String ERR_CANNOT_OBTAIN_HASHING_ALGORITHM = "Cannot obtain hashing algorithm";

    public static final String ERR_CANNOT_INSERT_ACCOUNT_INFO = "Cannot insert user and user info data to database";

}
