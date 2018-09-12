package com.dk.gfm.entity;

import java.sql.Timestamp;

/**
 * @ClassName User
 * @Description t_user表对应实体类
 * @Author xiadekang
 * @Date 2018/9/12 18:11
 * @Version 1.0
 **/
public class User {
    private long id;
    private Timestamp time;
    private String name;
    private String password;
    private String mobile;
    private int disable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", disable=" + disable +
                '}';
    }
}
