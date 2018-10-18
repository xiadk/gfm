package com.dk.gfm.common.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dk.gfm.common.Annotation.LoginRequired;
import com.dk.gfm.common.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName AuthenticationInterceptor
 * @Description TODO
 * @Author xiadekang
 * @Date 2018/9/25
 * @Version 1.0
 **/
public class AuthenticationInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private RedisTemplate<String, String> redis;
    /**
     * 在请求前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JSONObject result = new JSONObject();
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            // 判断是否存在令牌信息，如果存在，则允许登录
            Cookie cookie = Arrays.stream(request.getCookies()).filter(cok -> cok.getName().equals(Constants.TOKEN)).findFirst().orElse(new Cookie(Constants.TOKEN,""));
            String accessToken = cookie.getValue();
            /*if ("".equals(accessToken)) {
                response.sendRedirect("/login");

                return false;
            }*/
            String uid =  Optional.ofNullable((String)redis.opsForHash().get(accessToken,Constants.TOKEN)).orElse("");
            /*if("".equals(uid)) {
                response.sendRedirect("/login");

                return false;
            }*/
            request.setAttribute(Constants.UID,Long.valueOf(uid));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
