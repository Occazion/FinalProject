package com.epam.project.db;

import com.epam.project.db.entity.Tour;

public enum Status {
    OPENED, CONFIRMED, PAID, CLOSED;

    public static Status getStatus(Tour tour) {
        int statusId = tour.getStatusId();
        return Status.values()[statusId];
    }

    public static Status getStatus(int statusId) {
        return Status.values()[statusId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
