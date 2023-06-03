package com.example.demosecond;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.example.demosecond.dao")
//@ComponentScan("com.example.demosecond.dao")
public class DemoSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSecondApplication.class, args);
	}

}
