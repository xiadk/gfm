package com.dk.gfm.common;

/**
 * @ClassName CurrentUser
 * @Description 在Controller的方法参数中使用此注解，会自动根据token获取当前用户id。和LoginRequired注解一起使用
 * @Author xiadekang
 * @Date 2018/9/25
 * @Version 1.0
 **/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)          // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)     // 运行时有效
public @interface CurrentUser {
}