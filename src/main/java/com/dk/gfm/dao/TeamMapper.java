package com.dk.gfm.dao;

import com.dk.gfm.entity.Active;
import com.dk.gfm.entity.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ActiveMapper
 * @Description 团队类
 * @Author xiadekang
 * @Date 2018/9/29
 * @Version 1.0
 **/
@Repository
public interface TeamMapper {

    @Insert("insert into t_team (name, user_id, `desc`, total) values (#{name}, #{user_id}, #{desc}, #{total})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insertTeam(Team team);

    /**
     * @Author xiadekang
     * @Description 添加用户和团队映射关系
     * @Date 2018/10/17
     * @param []
     * @return void
     **/
    @Insert("insert into t_user_team (team_id, balance, username) values (#{arg0}, #{arg1}, #{arg2})")
    @Options(useGeneratedKeys=true)
    void insertUserTeam(long team_id, double balance, String username);

    @Select("SELECT name, `desc`, COUNT(username) as num, SUM(balance) as balance, total FROM t_team AS tt LEFT JOIN t_user_team AS tut ON tt.`id`=tut.`team_id` WHERE user_id=#{arg0}  GROUP BY tt.id")
    List<Map<String, Object>> selectTeam(long userId);
}
