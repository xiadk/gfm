package com.dk.gfm.common;

import java.io.Serializable;

/**
 * @ClassName ResultInfo
 * @Description 返回参数对象
 * @Author xiadekang
 * @Date 2018/9/10 11:10
 * @Version 1.0
 **/
public class ResultInfo implements Serializable{

    public final static int SUCCESS = 1;
    public final static int ERROR = -1;
    public final static int DATA_NOT_EXIST = -2;
    /**
     * 消息码。 默认：code = 0 成功：code > 0 失败：code < 1
     */
    public int code = 0;

    /** 提示信息 */
    public String msg = "亲，系统繁忙！";

    /** 结果集中的对象 */
    public Object obj;

    @Override
    public String toString() {
        return "ResultInfo [code=" + code + ", msg=" + msg + ", object=" + obj + "]";
    }

}
