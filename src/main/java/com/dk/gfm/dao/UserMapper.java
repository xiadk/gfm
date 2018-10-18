package com.dk.gfm.dao;

import com.dk.gfm.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description 用户数据层
 * @Author xiadekang
 * @Date 2018/9/12 18:06
 * @Version 1.0
 **/
@Repository
public interface UserMapper {
    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select id,name,age from t_user where id=#{id}")
    User selectById(@Param("id") Long id);

    @Select("select * from t_user where name=#{userName}")
    User findUserByName(String userName);

    @Insert("INSERT INTO t_user(name) VALUES(#{userName})")
    long insert(String userName);

}
