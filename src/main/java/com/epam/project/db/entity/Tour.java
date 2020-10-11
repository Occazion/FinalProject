package com.epam.project.db.entity;

public class Tour extends Entity {
    private String type;
    private String hotel;
    private int price;
    private int human_amount;
    private boolean fire;
    private int statusId;
    private int discount;
    private int user_id;

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

    public int getHuman_amount() {
        return human_amount;
    }

    public void setHuman_amount(int human_amount) {
        this.human_amount = human_amount;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id='" + getId() + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
