package com.learn.desingpatterns.service.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class JmsConsumerComponent {

	@JmsListener(destination = "${queue.new.user}")
	public void receiveMessage(String message) {
	    log.info("Received message: new user {}" , message);
	}
}
