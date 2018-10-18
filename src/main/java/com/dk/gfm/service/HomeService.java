package com.dk.gfm.service;

import com.dk.gfm.common.ResultInfo;
import com.dk.gfm.dao.ActiveMapper;
import com.dk.gfm.entity.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public ResultInfo active(Active active, boolean delivery){
        ResultInfo result = new ResultInfo();

        activeMapper.insertActive(active);
        long activeId = active.getId();
        if (activeId <= 0) {
            result.code = -1;
            result.msg = "添加活动失败";

            return result;
        }

        result.code = 1;
        result.msg = "添加活动成功";

        return result;
    }
}
