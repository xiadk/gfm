package com.dk.gfm.controller;

import com.dk.gfm.common.Annotation.CurrentUser;
import com.dk.gfm.common.Annotation.LoginRequired;
import com.dk.gfm.common.Enum.Disable;
import com.dk.gfm.common.ResultInfo;
import com.dk.gfm.entity.Active;
import com.dk.gfm.service.HomeService;
import com.dk.gfm.utli.Convert;
import com.dk.gfm.utli.VerifyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName HomeController
 * @Description TODO
 * @Author xiadekang
 * @Date 2018/9/7 9:59
 * @Version 1.0
 **/
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;


    @RequestMapping(value = "index")
    public String index(){

        System.out.println("我进来了");
        return "index";
    }

    @RequestMapping(value = "home")
    @LoginRequired
    public String home(@CurrentUser Long id){

        System.out.println("我进来了："+id);
        return "home";
    }

    @RequestMapping(value = "test")
    public String test(){

        return "test";
    }

    /**
     * @Author xiadekang
     * @Description 创建活动
     * @Date 2018/9/28
     * @return java.lang.String
     **/
    @RequestMapping(value = "active",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo active(@RequestBody Map<String, Object> userInfo){
        ResultInfo result = new ResultInfo();
        String activeName = Convert.orEles(userInfo.get("activeName"),StringUtils.EMPTY);
        String region = Convert.orEles(userInfo.get("region"),StringUtils.EMPTY);
        LocalDate date = LocalDate.parse(Convert.orEles(userInfo.get("date"),LocalDate.now()));
        LocalTime time = LocalTime.parse(Convert.orEles(userInfo.get("time"),LocalTime.now()));
        boolean delivery = Boolean.parseBoolean(Convert.orEles(userInfo.get("delivery"),Boolean.FALSE));
        int type = Integer.parseInt(Convert.orEles(userInfo.get("type"),0));
        LocalDateTime activeTime = LocalDateTime.of(date, time);
        String desc = Convert.orEles(userInfo.get("desc"),StringUtils.EMPTY);

        String[] str = Convert.orEles(userInfo.get("amount"),"0.00").split("[.]");
        if (str.length == 2 && str[1].length() > 2) {
            result.code = -1;
            result.msg = "最多保留两位小数";

            return result;
        }

        Active active = new Active();
        active.setActive_name(activeName);
        active.setRegion(region);
        active.setActive_type(type);
        active.setActive_time(Timestamp.valueOf(activeTime));
        active.setDesc(desc);
        active.setAmount(Double.parseDouble(str[1]));

        result = homeService.active(active, delivery);

        return result;
    }
}
