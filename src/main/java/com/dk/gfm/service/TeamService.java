package com.dk.gfm.service;

import com.alibaba.fastjson.JSONArray;
import com.dk.gfm.common.Enum.ParamsEnum;
import com.dk.gfm.common.ResultInfo;
import com.dk.gfm.dao.ActiveMapper;
import com.dk.gfm.dao.TeamMapper;
import com.dk.gfm.entity.Active;
import com.dk.gfm.entity.Team;
import com.dk.gfm.utli.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HomeService
 * @Description 团队业务类
 * @Author xiadekang
 * @Date 2018/9/29
 * @Version 1.0
 **/
@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    public ResultInfo addTeam(Team team, List<String> members, ParamsEnum.TeamType type, double amount){
        ResultInfo result = new ResultInfo();

        if (type == ParamsEnum.TeamType.TOTAL) {
            if (amount % members.size() != 0) {
                result.code = -1;
                result.msg = "不能平分";

                return result;
            }
            team.setTotal(amount);
            amount = Arith.div(amount, members.size(), 2);
        } else {
            team.setTotal(Arith.mul(amount,members.size()));
        }

        teamMapper.insertTeam(team);
        long teamId = team.getId();
        if (teamId <= 0) {
            result.code = -1;
            result.msg = "新建团队失败";

            return result;
        }

        for (int i = 0, j = members.size(); i < j ; i ++ ) {
            String username = members.get(i);
            teamMapper.insertUserTeam(teamId, amount, username);
        }

        result.code = 1;
        result.msg = "新建团队成功";

        return result;
    }

    public ResultInfo getTeams(long userId){
        ResultInfo result = new ResultInfo();

        List<Map<String, Object>> teams = teamMapper.selectTeam(userId);
        teams.forEach(team->{
            List<String> members = teamMapper.selectTeamMembers((Long) team.get("teamId"));
            team.put("members",members);
        });

        result.code = 1;
        result.msg = "获取团队信息成功";
        result.obj = teams;
        return result;

    }

    public ResultInfo deleteTeam(long userId, long teamId){
        ResultInfo result = new ResultInfo();

        int row = teamMapper.deleteTeamById(teamId, userId);
        if (row <= 0) {
            result.code = -1;
            result.msg = "非法操作";

            return result;
        }

        result.code = 1;
        result.msg = "删除成功";
        return result;

    }
}
