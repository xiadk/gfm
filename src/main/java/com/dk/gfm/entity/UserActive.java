package com.dk.gfm.entity;

/**
 * @ClassName UserActive
 * @Description 用户活动记录
 * @Author xiadekang
 * @Date 2018/10/26
 * @Version 1.0
 **/
public class UserActive {
    private long id;
    //活动id
    private long active_id;
    //成员姓名
    private String name;
    //活动经费
    private double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getActive_id() {
        return active_id;
    }

    public void setActive_id(long active_id) {
        this.active_id = active_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
