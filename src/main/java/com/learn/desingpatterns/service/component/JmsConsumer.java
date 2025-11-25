package com.learn.desingpatterns.service.component;

import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JmsConsumer {

  private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

  @JmsListener(destination = "test.queue")
  public void receiveMessage(String message) {
    logger.info("Received message: {}", message);
  }
}
