package com.managment.system.Entity;

public class LogInfo {
    private int id;
    private String username;
    private String rolo;
    private String time;
    private String result;
    private String event;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRolo(String rolo) {
        this.rolo = rolo;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public String getTime() {
        return time;
    }

    public String getResult() {
        return result;
    }

    public String getEvent() {
        return event;
    }

    public LogInfo(int id, String username, String rolo, String time, String result, String event) {
        this.id = id;
        this.username = username;
        this.rolo = rolo;
        this.time = time;
        this.result = result;
        this.event = event;
    }
}
