package com.learn.desingpatterns.service;

import org.springframework.stereotype.Service;
import org.springframework.jms.core.JmsMessagingTemplate;

@Service
public class JmsProducer {

  private final JmsMessagingTemplate jmsMessagingTemplate;


  public JmsProducer(JmsMessagingTemplate jmsMessagingTemplate) {
    this.jmsMessagingTemplate = jmsMessagingTemplate;
  }

  public void sendMessage(String message) {
    this.jmsMessagingTemplate.convertAndSend("test.queue", message);
  }
}
