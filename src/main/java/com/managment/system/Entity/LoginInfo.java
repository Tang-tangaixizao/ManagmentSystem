package com.managment.system.Entity;

public class LoginInfo {
    private int id;
    private String username;
    private String logintime;
    private String loginIP;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getUsername() {
        return username;
    }

    public String getLogintime() {
        return logintime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public int getId() {
        return id;
    }

    public LoginInfo(int id, String username, String logintime, String loginIP) {
        this.id = id;
        this.username = username;
        this.logintime = logintime;
        this.loginIP = loginIP;
    }
}
