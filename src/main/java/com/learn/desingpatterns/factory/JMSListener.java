package com.learn.desingpatterns.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JMSListener {


    @JmsListener(destination = "${customdata.jms.topic}")
    public void listen(String in) {
        log.info("Este en el mensaje que se envió ".concat(in));
    }
}
