package com.managment.system.Entity;

public class DiskInfo {
    private long size;
    private String name;

    public void setSize(long size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public DiskInfo(long size, String name) {
        this.size = size;
        this.name = name;
    }
}
