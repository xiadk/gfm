package com.dk.gfm.dao;

import com.dk.gfm.entity.Active;
import com.dk.gfm.entity.UserActive;
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
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertActive(Active active);

    /**
     * @Author xiadekang
     * @Description 插入用户活动记录
     * @Date 2018/10/26
     * @return void
     **/
    @Insert("insert into t_user_active (active_id, name , amount) values (#{active_id}, #{name}, #{amount})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insertUserActive(UserActive userActive);
}
