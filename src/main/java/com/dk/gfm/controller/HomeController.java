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
import java.util.*;

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
    @LoginRequired
    @ResponseBody
    public ResultInfo active(@RequestBody Map<String, Object> userInfo){
        ResultInfo result = new ResultInfo();
        //活动名称
        String activeName = Convert.orEles(userInfo.get("activeName"),StringUtils.EMPTY);
        //活动地区
        String region = Convert.orEles(userInfo.get("region"),StringUtils.EMPTY);
        //活动时间
        LocalDate date = LocalDate.parse(Convert.orEles(userInfo.get("date"),LocalDate.now()));
        LocalTime time = LocalTime.parse(Convert.orEles(userInfo.get("time"),LocalTime.now()));
        //是否发送短信
        boolean delivery = Boolean.parseBoolean(Convert.orEles(userInfo.get("delivery"),Boolean.FALSE));
        //活动类型
        int type = Integer.parseInt(Convert.orEles(userInfo.get("type"),0));
        LocalDateTime activeTime = LocalDateTime.of(date, time);
        //描述
        String desc = Convert.orEles(userInfo.get("desc"),StringUtils.EMPTY);

        //活动经费
        Double amount = Double.parseDouble(Convert.orEles(userInfo.get("amount"),0.00));
        if ( amount == 0){
            result.code = -1;
            result.msg = "活动经费未填写";

            return result;
        }
        String[] str = amount.toString().split("[.]");
        if (str.length == 2 && str[1].length() > 2) {
            result.code = -1;
            result.msg = "最多保留两位小数";

            return result;
        }

        //团队Id
        Long teamId = Long.parseLong(Convert.orEles(userInfo.get("team"),0));
        if (teamId == 0) {
            result.code = -1;
            result.msg = "未选择团队";

            return result;
        }

        //成员
        List<String> members = (List<String>) userInfo.get("members");
        if (members == null || members.size() == 0) {
            result.code = -1;
            result.msg = "未选择成员";

            return result;
        }


        Active active = new Active();
        active.setActive_name(activeName);
        active.setRegion(region);
        active.setActive_type(type);
        active.setActive_time(Timestamp.valueOf(activeTime));
        active.setDesc(desc);
        active.setAmount(amount);
        active.setTeam_id(teamId);

        result = homeService.active(active, delivery, members);

        return result;
    }

    @RequestMapping(value = "active",method = RequestMethod.GET)
    @LoginRequired
    @ResponseBody
    public ResultInfo getActive(@CurrentUser long userId ,@RequestParam String activeName) {
        ResultInfo result  = new ResultInfo();

        result = homeService.getActive();

        result.code = 1;
        result.msg = "获取成功";
        return  result;
    }
}
