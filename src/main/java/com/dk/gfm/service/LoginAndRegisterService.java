package com.dk.gfm.service;

import com.dk.gfm.common.Constants;
import com.dk.gfm.common.ResultInfo;
import com.dk.gfm.dao.UserMapper;
import com.dk.gfm.entity.User;
import com.dk.gfm.utli.ImageCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LoginAndRegisterService
 * @Description 登陆注册页面
 * @Author xiadekang
 * @Date 2018/9/10 10:51
 * @Version 1.0
 **/
@Service
public class LoginAndRegisterService {

    @Autowired
    private RedisTemplate<String, String> redis;
    @Autowired
    private UserMapper userMapper;
    /**
     *
     *
     * @return
     */
    public ResultInfo imageCode(){

        ResultInfo resultInfo = ImageCodeUtil.getImageCode();
        List<Object> res = (List)resultInfo.obj;

        String sRand = String.valueOf(res.get(0));
        redis.opsForValue().set(sRand,sRand,30,TimeUnit.SECONDS);

        return resultInfo;
    }

    public ResultInfo doLogin(String userName, String imageCode){
        ResultInfo resultInfo = new ResultInfo();
        String checkImageCode = redis.opsForValue().get(imageCode);
        if (StringUtils.isBlank(checkImageCode)) {
            resultInfo.code = ResultInfo.ERROR;
            resultInfo.msg = "验证码错误";

            return resultInfo;
        }

        boolean del = redis.delete(imageCode);
        User user = userMapper.findUserByName(userName);
        if (user == null) {
            resultInfo.code = ResultInfo.DATA_NOT_EXIST;
            resultInfo.msg = "用户名未注册";

            return  resultInfo;
        }

        String token = UUID.randomUUID().toString().replace("-","");
        redis.opsForHash().put(token, Constants.TOKEN, String.valueOf(user.getId()));

        resultInfo.code = ResultInfo.SUCCESS;
        resultInfo.msg ="登陆成功";
        resultInfo.obj = token;

        return resultInfo;
    }

    public ResultInfo doRegister(String userName){
        ResultInfo resultInfo = new ResultInfo();

        User user = userMapper.findUserByName(userName);
        if (user != null) {
            resultInfo.code = ResultInfo.DATA_NOT_EXIST;
            resultInfo.msg = "用户名已被注册";

            return  resultInfo;
        }

        long row = userMapper.insert(userName);
        if (row <= 0) {
            resultInfo.code = ResultInfo.ERROR;
            resultInfo.msg = "注册失败";

            return resultInfo;
        }

        resultInfo.code = ResultInfo.SUCCESS;
        resultInfo.msg ="注册成功";

        return resultInfo;
    }

}
