package com.dk.gfm.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName MyExceptionHandler
 * @Description 全局异常处理器
 * @Author xiadekang
 * @Date 2018/10/29
 * @Version 1.0
 **/
@RestControllerAdvice
public class MyExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    public ResultInfo handle(Exception e) {
        ResultInfo result = new ResultInfo();
        if(e instanceof MyException) {
            MyException myException = (MyException)e;

            result.code = myException.getCode();
            result.msg = myException.getMessage();
            return result;
        }else {
            logger.error("系统异常", e);

            result.code = -1;
            result.msg = "服务器错误";
            return result;
        }
    }
}
