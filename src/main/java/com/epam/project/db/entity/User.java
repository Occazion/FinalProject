package com.epam.project.db.entity;

public class User extends Entity {

    private int roleId;

    private String login;

    private String password;

    private String locale;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", roleId=" + roleId +
                ", login='" + login + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }
}
