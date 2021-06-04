package com.managment.system.Entity;

public class WarningInfo {
    private int id;
    private String time;
    private String type;
    private String explain;

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getExplain() {
        return explain;
    }
}
