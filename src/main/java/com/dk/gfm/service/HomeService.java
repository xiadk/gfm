package com.dk.gfm.service;

import com.dk.gfm.common.ResultInfo;
import com.dk.gfm.dao.ActiveMapper;
import com.dk.gfm.dao.TeamMapper;
import com.dk.gfm.entity.Active;
import com.dk.gfm.entity.UserActive;
import com.dk.gfm.utli.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public ResultInfo active(Active active, boolean delivery, List<String> members) {
        ResultInfo result = new ResultInfo();

        activeMapper.insertActive(active);
        long activeId = active.getId();
        if (activeId <= 0) {
            result.code = -1;
            result.msg = "添加活动失败";

            return result;
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
                result.code = -1;
                result.msg = "添加用户:"+username+"记录失败";

                return result;
            }
            int teamRow = teamMapper.updateBalance(username, activeId, averageAmount);
            if (teamRow <= 0) {
                result.code = -1;
                result.msg = "更新用户:"+username+"金额失败";

                return result;
            }
        }


        result.code = 1;
        result.msg = "添加活动成功";

        return result;
    }
}
