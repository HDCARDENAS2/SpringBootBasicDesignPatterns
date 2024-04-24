package com.learn.desingpatterns.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.learn.desingpatterns.dto.UserDTO;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class ServiceLogicLoggingAspect {

    @Before("execution(* com.learn.desingpatterns.service.UserService.save(..)) && args(userDTO)")
    public void beforeSaveUser(UserDTO userDTO) {
    	log.info("Logging before saving UserDTO : {} " + userDTO.toString());
    }

    @AfterReturning(pointcut = "execution(* com.learn.desingpatterns.service.UserService.save(..))", returning = "userDTO")
    public void afterSaveUser(UserDTO userDTO) {
    	log.info("Logging after saving UserDTO: " + userDTO.toString());
    }

}

