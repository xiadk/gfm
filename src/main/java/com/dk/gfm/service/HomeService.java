package com.dk.gfm.service;

import com.dk.gfm.common.MyException;
import com.dk.gfm.common.ResultInfo;
import com.dk.gfm.dao.ActiveMapper;
import com.dk.gfm.dao.TeamMapper;
import com.dk.gfm.entity.Active;
import com.dk.gfm.entity.UserActive;
import com.dk.gfm.utli.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName HomeService
 * @Description 首页业务类
 * @Author xiadekang
 * @Date 2018/9/29
 * @Version 1.0
 **/
@Service
public class HomeService {

    @Autowired
    private ActiveMapper activeMapper;
    @Autowired
    private TeamMapper teamMapper;

    @Transactional
    public ResultInfo active(Active active, boolean delivery, List<String> members){
        ResultInfo result = new ResultInfo();

        activeMapper.insertActive(active);
        long activeId = active.getId();
        if (activeId <= 0) {

            throw new MyException(-1, "添加活动失败");
        }

        //平均金额
        double averageAmount = Arith.div(active.getAmount(), members.size(), 2);
        double totalAmount = 0;
        for (int i = 0, j = members.size(); i < j ; i ++) {
            //将剩余金额都给最后一个人
            if (i == j) {
                averageAmount = Arith.sub(active.getAmount(),totalAmount);
            } else {
                totalAmount += averageAmount;
            }

            String username = members.get(i);

            UserActive userActive = new UserActive();
            userActive.setActive_id(activeId);
            userActive.setAmount(averageAmount);
            userActive.setName(username);
            //添加成员
            activeMapper.insertUserActive(userActive);
            if (userActive.getActive_id() <= 0) {

                throw new MyException(-1, "添加用户:"+username+"记录失败");
            }
            int teamRow = teamMapper.updateBalance(username, active.getTeam_id(), averageAmount);
            if (teamRow <= 0) {

                throw new MyException(-1, username+"余额已不足");
            }
        }


        result.code = 1;
        result.msg = "添加活动成功";

        return result;
    }

    /**
     * @Author xiadekang
     * @Description 获取活动记录
     * @Date 2018/10/29
     * @param []
     * @return com.dk.gfm.common.ResultInfo
     **/
    public ResultInfo getActive(){
        ResultInfo result = new ResultInfo();

        return result;
    }
}
