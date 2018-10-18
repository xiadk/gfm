package com.dk.gfm.dao;

import com.dk.gfm.entity.Active;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @ClassName ActiveMapper
 * @Description 活动类
 * @Author xiadekang
 * @Date 2018/9/29
 * @Version 1.0
 **/
@Repository
public interface ActiveMapper {

    @Insert("insert into t_active (active_name, active_time, region, active_type, `desc`, amount) values (#{active_name}, #{active_time}, #{region}, #{active_type}, #{desc}, #{amount})")
    @Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="id")
    void insertActive(Active active);
}
