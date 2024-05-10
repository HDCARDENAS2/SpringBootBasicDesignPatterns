package com.learn.desingpatterns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn.desingpatterns.factory.JmsFactoryCustom;
import com.learn.desingpatterns.factory.JmsMessagingCustom;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsCustomConfig {

    @Value("${customdata.jms.topic}")
    private String topic;

    @Bean(name = "jmsMessagingCustom")
    JmsFactoryCustom jmsFactoryCustom(JmsTemplate jmsTemplate) {
        JmsFactoryCustom jmsFactoryCustom = new JmsFactoryCustom(jmsTemplate);
        jmsFactoryCustom.setTopic(topic);
        return jmsFactoryCustom;
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) throws Exception {
        return new JmsTemplate(connectionFactory);
    }

    JmsMessagingCustom jmsMessagingCustom(JmsTemplate jmsTemplate) throws Exception {
        return jmsFactoryCustom(jmsTemplate).getObject();
    }
}
