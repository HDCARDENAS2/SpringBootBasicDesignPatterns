package com.learn.desingpatterns.service.component;

import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JmsConsumer {

	@JmsListener(destination = "test.queue")
	public void receiveMessage(String message) {
		log.info("Received message at the consumer: " + message);
	}
}
