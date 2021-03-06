package com.dk.gfm;

import com.dk.gfm.dao.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GfmApplicationTests {

	@Autowired
	private RedisTemplate<String, String> redis;
	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {

		double amount = new BigDecimal(1.5758).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(amount);
//		userMapper.insert("dkk");

	}

}
