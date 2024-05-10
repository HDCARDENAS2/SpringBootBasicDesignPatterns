package com.learn.desingpatterns.factory;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.core.JmsTemplate;

@Getter
@Log4j2
public class JmsMessagingCustom {

    private String topic;
    private final JmsTemplate jmsTemplate;


    public JmsMessagingCustom(String topic, JmsTemplate jmsTemplate) {
        super();
        this.topic = topic;
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String message) {
        log.info("JMS message {} sent to {} ", message, topic);
        jmsTemplate.convertAndSend(topic, message);
    }

}
