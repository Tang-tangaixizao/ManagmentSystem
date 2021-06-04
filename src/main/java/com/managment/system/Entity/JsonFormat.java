package com.managment.system.Entity;

public class JsonFormat <T>{
    private int code;
    private String msg;
    private int count;
    private T[] data;

    public void setCode(int code) {
        this.code = code;
    }

    public JsonFormat(int code, String msg, int count, T[] data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setData(T[] data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCount() {
        return count;
    }

    public T[] getData() {
        return data;
    }
}
