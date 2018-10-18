package com.dk.gfm.common.Enum;

import java.util.Arrays;

/**
 * @ClassName ParamsEnum
 * @Description 参数枚举
 * @Author xiadekang
 * @Date 2018/10/17
 * @Version 1.0
 **/
public class ParamsEnum {
    public enum TeamType{
        AVERAGE(1,"人均"),TOTAL(2,"总金额");

        public int code;
        public String desc;

        private TeamType(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public static TeamType getTeamType (int code){
            return Arrays.stream(TeamType.values()).filter(teamType -> teamType.code == code).findFirst().orElse(AVERAGE);
        }
    }
}
