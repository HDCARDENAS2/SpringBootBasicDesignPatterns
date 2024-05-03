package com.learn.desingpatterns.service.impl;


import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {


  private JmsMessagingTemplate jmsMessagingTemplate;

  public void sendMessage(String message) {
    this.jmsMessagingTemplate.convertAndSend("test.queue", message);
  }
}