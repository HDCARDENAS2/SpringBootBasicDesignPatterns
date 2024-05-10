package com.learn.desingpatterns.factory;

import org.springframework.beans.factory.FactoryBean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jms.core.JmsTemplate;

@Getter
@Setter
public class JmsFactoryCustom implements FactoryBean<JmsMessagingCustom> {

    private String topic;
    private final JmsTemplate jmsTemplate;

    public JmsFactoryCustom(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public JmsMessagingCustom getObject() throws Exception {
        return new JmsMessagingCustom(topic, jmsTemplate);
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
