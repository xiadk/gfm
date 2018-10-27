package com.dk.gfm.entity;

import java.sql.Timestamp;
import java.util.Arrays;

public class Active {
    private long id;
    private Timestamp active_time;
    private String active_name;
    private String region;
    private int active_type;
    private String desc;
    private int disable;
    private double amount;
    private long team_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Timestamp getActive_time() {
        return active_time;
    }

    public void setActive_time(Timestamp active_time) {
        this.active_time = active_time;
    }

    public String getActive_name() {
        return active_name;
    }

    public void setActive_name(String active_name) {
        this.active_name = active_name;
    }

    public int getActive_type() {
        return active_type;
    }

    public void setActive_type(int active_type) {
        this.active_type = active_type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(long team_id) {
        this.team_id = team_id;
    }
}
