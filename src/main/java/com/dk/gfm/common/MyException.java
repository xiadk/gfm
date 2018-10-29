package com.dk.gfm.common;

/**
 * @ClassName MyException
 * @Description 自定义异常类
 * @Author xiadekang
 * @Date 2018/10/29
 * @Version 1.0
 **/
public class MyException extends RuntimeException{
    private int code;

    public MyException(int code, String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
