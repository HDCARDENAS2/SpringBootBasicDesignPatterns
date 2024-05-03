package com.learn.desingpatterns.config;

import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class BrokerServiceConfig {

	@Value("${spring.activemq.broker-url}")
	private String endPoint;
	
    @Bean
    BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector(endPoint);
        broker.setUseShutdownHook(false);
        return broker;
    }
    
}
