package com.learn.desingpatterns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn.desingpatterns.factory.JmsFactoryCustom;
import com.learn.desingpatterns.factory.JmsMessagingCustom;

@Configuration
public class JmsCustomConfig {

	@Value("${customdata.jms.topic}")
	private String topic;
	
	@Bean(name = "jmsMessagingCustom")
    JmsFactoryCustom jmsFactoryCustom() {
    	JmsFactoryCustom jmsFactoryCustom = new JmsFactoryCustom();
    	jmsFactoryCustom.setTopic(topic);
        return jmsFactoryCustom;
    }
    
    @Bean
    JmsMessagingCustom jmsMessagingCustom() throws Exception {
        return jmsFactoryCustom().getObject();
    }
}
