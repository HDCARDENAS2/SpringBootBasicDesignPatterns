package com.learn.desingpatterns.factory;

import org.springframework.beans.factory.FactoryBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JmsFactoryCustom implements FactoryBean<JmsMessagingCustom> {

	private String topic;
	
	@Override
	public JmsMessagingCustom getObject() throws Exception {
		return new JmsMessagingCustom(topic);
	}

	@Override
	public Class<?> getObjectType() {
		return JmsMessagingCustom.class;
	}
	
	@Override
    public boolean isSingleton() {
        return false;
    }

}
