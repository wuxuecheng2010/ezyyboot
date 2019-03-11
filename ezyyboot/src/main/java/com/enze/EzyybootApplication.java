package com.enze;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages="com.enze.dao")
@EnableScheduling
public class EzyybootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzyybootApplication.class, args);
	}

}

