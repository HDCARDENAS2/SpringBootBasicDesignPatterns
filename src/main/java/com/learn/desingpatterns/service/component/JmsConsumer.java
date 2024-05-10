package com.learn.desingpatterns.service.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.learn.desingpatterns.aspect.ServiceLoggingAspect;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JmsConsumer {

	@JmsListener(destination = "test.queue")
	public void receiveMessage(String message) {		
		log.info("Received message: " + message);
	}

}
