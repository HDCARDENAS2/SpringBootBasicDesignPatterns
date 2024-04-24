package com.learn.desingpatterns.factory;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public class JmsMessagingCustom {

	private String topic;
	
    public JmsMessagingCustom(String topic) {
		super();
		this.topic = topic;
	}

	public void send(String message) {
		  log.info("JMS message {} sent to {} ", message, topic);
    } 
	
}
