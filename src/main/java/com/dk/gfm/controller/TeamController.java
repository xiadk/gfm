package com.dk.gfm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dk.gfm.common.Annotation.CurrentUser;
import com.dk.gfm.common.Annotation.LoginRequired;
import com.dk.gfm.common.Enum.ParamsEnum;
import com.dk.gfm.common.ResultInfo;
import com.dk.gfm.dao.TeamMapper;
import com.dk.gfm.entity.Active;
import com.dk.gfm.entity.Team;
import com.dk.gfm.service.HomeService;
import com.dk.gfm.service.TeamService;
import com.dk.gfm.utli.Convert;
import netscape.javascript.JSObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @Author xiadekang
 * @Description 我的团队
 * @Date 2018/10/16
 * @param 
 * @return 
 **/
@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamMapper teamMapper;

    /**
     * @Author xiadekang
     * @Description 创建团队
     * @Date 2018/10/16
     * @param userId 用户id
     * @return java.lang.String
     **/
    @RequestMapping(value = "team",method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public ResultInfo addTeam(@CurrentUser Long userId, @RequestBody Map<String, Object> teamInfo){
        ResultInfo result = new ResultInfo();
        String teamName = Convert.orEles(teamInfo.get("teamName"),StringUtils.EMPTY);
        if (StringUtils.isBlank(teamName)) {
            result.code = -1;
            result.msg = "团队名称为空";

            return result;
        }
        ParamsEnum.TeamType type = ParamsEnum.TeamType.getTeamType(Integer.parseInt(Convert.orEles(teamInfo.get("type"),1)));
        String desc = Convert.orEles(teamInfo.get("desc"),StringUtils.EMPTY);
        List<String> members = (ArrayList)teamInfo.get("member");
        String amountStr = Convert.orEles(teamInfo.get("amount"),"0.00");
        String[] str = amountStr.split("[.]");
        if (str.length == 2 && str[1].length() > 2) {
            result.code = -1;
            result.msg = "最多保留两位小数";

            return result;
        }
        double amount = Double.parseDouble(amountStr);

        Team team = new Team();
        team.setDesc(desc);
        team.setName(teamName);
        team.setUser_id(userId);

        result = teamService.addTeam(team, members, type, amount);

        return result;
    }

    /**
     * @Author xiadekang
     * @Description 获取团队信息
     * @Date 2018/10/17
     * @return com.dk.gfm.common.ResultInfo
     **/
    @RequestMapping(value = "team",method = RequestMethod.GET)
    @LoginRequired
    @ResponseBody
    public ResultInfo getTeam(@CurrentUser Long userId) {
        ResultInfo result  = new ResultInfo();

        result = teamService.getTeams(userId);

        return  result;
    }

    @RequestMapping(value = "team",method = RequestMethod.DELETE)
    @LoginRequired
    @ResponseBody
    public ResultInfo deleteTeam(@CurrentUser Long userId, @RequestBody Map<String, Object> teamInfo) {
        ResultInfo result  = new ResultInfo();

        Object teamIdObj = teamInfo.get("teamId");
        if (teamIdObj == null) {
            result.code = -1;
            result.msg = "非法操作";

            return result;
        }
        long teamId =Long.parseLong(Convert.orEles(teamIdObj, 1));
        result = teamService.deleteTeam(userId, teamId);

        return  result;
    }

    @RequestMapping(value = "members",method = RequestMethod.GET)
    @LoginRequired
    @ResponseBody
    public ResultInfo getMembers(@CurrentUser Long userId, @RequestParam long teamId) {
        ResultInfo result  = new ResultInfo();

        List<String> members = teamMapper.selectTeamMembers(teamId);

        result.code = 1;
        result.msg = "获取成功";
        result.obj = members;
        return  result;
    }
}
