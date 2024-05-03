package com.learn.desingpatterns.config;

import java.util.Collections;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import com.learn.desingpatterns.factory.JmsFactoryCustom;
import com.learn.desingpatterns.factory.JmsMessagingCustom;

@Configuration
public class JmsCustomConfig {

	@Value("${customdata.jms.topic}")
	private String topic;
	
	
	@Value("${spring.activemq.broker-url}")
	private String urlMq;
	
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
    
    
    // Configuración de la conexión a ActiveMQ
    @Bean
    public ConnectionFactory connectionFactory() {
    	 ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    	  // URL del broker de ActiveMQ
         connectionFactory.setBrokerURL(urlMq); 

         // Configurar los paquetes de confianza
         connectionFactory.setTrustedPackages(Collections.singletonList("com.learn.desingpatterns"));

         // Asegurarse de que setTrustAllPackages no esté configurado en true
         connectionFactory.setTrustAllPackages(false);

         return new CachingConnectionFactory(connectionFactory);
    }

    // Configuración del JmsMessagingTemplate
    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ConnectionFactory connectionFactory) {
        return new JmsMessagingTemplate(connectionFactory);
    }
}
