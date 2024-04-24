package com.learn.desingpatterns.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class ServiceLoggingAspect {
    
    @Before("execution(* com.learn.desingpatterns.service.*.*(..))")
    public void beforeServiceMethods() {
        log.info("Logging before service methods...");
    }
    
}  