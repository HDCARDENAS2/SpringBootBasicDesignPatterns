package com.learn.desingpatterns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn.desingpatterns.custom.EmailCustom;

@Configuration
public class EmailCustomConfig {

	@Value("${customdata.email.endpoint}")
	private String endPoint;

	@Bean
	EmailCustom customEmail() {
		return new EmailCustom(endPoint);
	}
	
}
