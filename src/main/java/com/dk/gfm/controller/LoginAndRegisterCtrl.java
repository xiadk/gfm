package com.dk.gfm.controller;

import com.dk.gfm.common.Constants;
import com.dk.gfm.common.ResultInfo;
import com.dk.gfm.dao.UserMapper;
import com.dk.gfm.service.LoginAndRegisterService;
import com.dk.gfm.utli.ImageCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginAndRegisterCtrl
 * @Description 登陆注册页面视图层
 * @Author xiadekang
 * @Date 2018/9/10 10:47
 * @Version 1.0
 **/
@Controller
public class LoginAndRegisterCtrl {
    @Autowired
    private LoginAndRegisterService loginAndRegisterService ;

    @RequestMapping(value = "login")
    public String login(){

        return "login";
    }

    @RequestMapping(value =  "doRegister",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo doRegister(@RequestBody Map<String, Object> userInfo){
        ResultInfo resultInfo = new ResultInfo();

        String userName = String.valueOf(userInfo.get("username"));
        if (StringUtils.isBlank(userName)) {
            resultInfo.code = ResultInfo.ERROR;
            resultInfo.msg = "用户名不能为空";

            return resultInfo;
        }
        loginAndRegisterService.doRegister(userName);


        return resultInfo;
    }

    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo doLogin(@RequestBody Map<String, Object> userInfo,HttpServletResponse response){
        ResultInfo resultInfo = new ResultInfo();

        String userName = String.valueOf(userInfo.get("username"));
        String imageCode = String.valueOf(userInfo.get("imageCode"));
        if (StringUtils.isBlank(userName)) {
            resultInfo.code = ResultInfo.ERROR;
            resultInfo.msg = "用户名不能为空";

            return resultInfo;
        }
        if (StringUtils.isBlank(imageCode)) {
            resultInfo.code = ResultInfo.ERROR;
            resultInfo.msg = "验证码不能为空";

            return resultInfo;
        }

        resultInfo = loginAndRegisterService.doLogin(userName, imageCode);
        Cookie cookie = new Cookie(Constants.TOKEN,String.valueOf(resultInfo.obj));
        cookie.setMaxAge(60*60*24*7);
        response.addCookie(cookie);

        return resultInfo;
    }

    @RequestMapping(value="imageCode")
    public String imageCode( HttpServletResponse response){

        ResultInfo resultInfo = loginAndRegisterService.imageCode();
        List<Object> res = (List)resultInfo.obj;
        BufferedImage bufferedImage = (BufferedImage)res.get(1);

        ServletOutputStream responseOutputStream = null;
        try {
            responseOutputStream = response.getOutputStream();
            // 输出图象到页面
            ImageIO.write(bufferedImage, "JPEG", responseOutputStream);
        } catch (IOException e) {
            e.printStackTrace();

            return "";
        }

        return  null;
    }
}
