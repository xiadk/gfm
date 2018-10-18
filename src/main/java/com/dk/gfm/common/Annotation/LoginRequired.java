package com.dk.gfm.common.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @ClassName LoginRequired
 * @Description 在需要登录验证的Controller的方法上使用此注解
 * @Author xiadekang
 * @Date 2018/9/25
 * @Version 1.0
 **/

@Target({ElementType.METHOD})// 可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface LoginRequired {
}