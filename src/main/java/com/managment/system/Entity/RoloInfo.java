package com.managment.system.Entity;

public class RoloInfo {
    private int id;
    private String rolo;
    private String power;

    public RoloInfo(int id, String rolo, String power) {
        this.id = id;
        this.rolo = rolo;
        this.power = power;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRolo(String rolo) {
        this.rolo = rolo;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public String getRolo() {
        return rolo;
    }

    public String getPower() {
        return power;
    }
}
