package com.learn.desingpatterns.service.component;

import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.learn.desingpatterns.event.UserCreatedEvent;

@Component
public class JmsConsumer {
	

	  private JmsMessagingTemplate jmsMessagingTemplate;
	  
	  
	  

	  public JmsConsumer(JmsMessagingTemplate jmsMessagingTemplate) {
		
		this.jmsMessagingTemplate = jmsMessagingTemplate;
	}

  @JmsListener(destination = "test.queue")
  public void receiveMessage(String mensage) {
	 
    System.out.println("Received message: " + mensage);
  }
  
  @EventListener
  public void sendMessage(UserCreatedEvent userCreatedEvent) {
	  this.jmsMessagingTemplate.convertAndSend("test.queue", userCreatedEvent.getUser().getId());
    System.out.println("Received message: " + userCreatedEvent.getUser().getName());
  }
}