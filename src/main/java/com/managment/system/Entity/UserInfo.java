package com.managment.system.Entity;

public class UserInfo {
    private int id;
    private String faceicon;
    private String username;
    private String name;
    private String iphone;
    private int age;
    private String sex;
    private String rolo;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFaceicon(String faceicon) {
        this.faceicon = faceicon;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setRolo(String rolo) {
        this.rolo = rolo;
    }

    public int getId() {
        return id;
    }

    public String getFaceicon() {
        return faceicon;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getIphone() {
        return iphone;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getRolo() {
        return rolo;
    }
}
