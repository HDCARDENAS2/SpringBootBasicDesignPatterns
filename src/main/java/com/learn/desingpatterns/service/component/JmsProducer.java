package com.learn.desingpatterns.service.component;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class JmsProducer {

	private final JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	public JmsProducer(JmsMessagingTemplate jmsMessagingTemplate){
		this.jmsMessagingTemplate = jmsMessagingTemplate;
	}

	public void sendMessage(String message) {
		this.jmsMessagingTemplate.convertAndSend("test.queue", message);
		log.info("Sent message from the producer");
	}
}