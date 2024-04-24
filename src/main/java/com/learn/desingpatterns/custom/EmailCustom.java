package com.learn.desingpatterns.custom;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public class EmailCustom {

	private String endPoint;

	public EmailCustom(String endPoint) {
		super();
		this.endPoint = endPoint;
	}
	
	public void send(String email) {
		log.info("EmailCustom service sending email to {} with endPoint {} ", email, endPoint);
	}
	
}
