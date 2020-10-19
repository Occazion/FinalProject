package com.epam.project.db.entity;

public class Tour extends Entity {
    private String type;
    private String hotel;
    private int price;
    private int humanAmount;
    private boolean fire;
    private int statusId;
    private int discount;
    private int userId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHumanAmount() {
        return humanAmount;
    }

    public void setHumanAmount(int humanAmount) {
        this.humanAmount = humanAmount;
    }

    public boolean getFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id='" + getId() + '\'' +
                ", user_id=" + userId +
                '}';
    }
}
