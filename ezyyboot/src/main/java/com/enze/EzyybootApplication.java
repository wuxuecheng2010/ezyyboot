package com.enze;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.enze.config.DBConfigERP;
import com.enze.config.DBConfigMapserver;
import com.enze.config.DBConfigPMS;
import com.enze.config.DBConfigWMS;

@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages="com.enze.dao")
@EnableScheduling
@EnableConfigurationProperties(value= {DBConfigERP.class,DBConfigWMS.class,DBConfigMapserver.class,DBConfigPMS.class})
public class EzyybootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzyybootApplication.class, args);
	}

}

