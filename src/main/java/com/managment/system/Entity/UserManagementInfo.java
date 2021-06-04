package com.managment.system.Entity;

public class UserManagementInfo {
    private int id;
    private String username;
    private String rolo;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRolo(String rolo) {
        this.rolo = rolo;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRolo() {
        return rolo;
    }

    public UserManagementInfo(int id, String username, String rolo) {
        this.id = id;
        this.username = username;
        this.rolo = rolo;
    }
}
