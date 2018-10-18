package com.dk.gfm.common.Enum;

import java.util.Arrays;

/**
 * @ClassName Disable
 * @Description disable字段枚举类
 * @Author xiadekang
 * @Date 2018/9/28
 * @Version 1.0
 **/
public enum Disable{
    ABLE(1,"启用"),DISABLE(2,"禁用");

    public int code;
    public String desc;

    private Disable(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static Disable getDisable (int code){
        return Arrays.stream(Disable.values()).filter(disable -> disable.code == code).findFirst().orElse(ABLE);
    }
}