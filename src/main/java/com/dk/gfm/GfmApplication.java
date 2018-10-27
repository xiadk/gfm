package com.dk.gfm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.dk.gfm.dao")
public class GfmApplication {

	public static void main(String[] args) {
		SpringApplication.run(GfmApplication.class, args);
	}
}
