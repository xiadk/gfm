package com.dk.gfm.entity;

/**
 * @ClassName Team
 * @Description 团队
 * @Author xiadekang
 * @Date 2018/10/17
 * @Version 1.0
 **/
public class Team {
    private long id;
    private String name;
    private long user_id;
    private String desc;
    private double total;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
