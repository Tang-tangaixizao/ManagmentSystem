package com.managment.system.Entity;

import java.util.List;

public class MyNetInfo {
    private String name;
    private List<Long> list;

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public List<Long> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "MyNetInfo{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }

    public MyNetInfo(String name, List<Long> list) {
        this.name = name;
        this.list = list;
    }
}
