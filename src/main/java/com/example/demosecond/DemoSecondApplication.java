package com.example.demosecond;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement // 开启事务，保证redis与mysql中数据的一致性
@MapperScan("com.example.demosecond.dao")
//@ComponentScan("com.example.demosecond.dao")
public class DemoSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSecondApplication.class, args);
	}

}
