package com.learn.desingpatterns.service.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.learn.desingpatterns.custom.EmailCustom;
import com.learn.desingpatterns.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class EmailComponent {
	
	private final EmailCustom emailCustom;
	@Value("${customdata.email.salesTeam}")
	private String salesTeamEmail;
	
    @EventListener
    public void sendEmailToNewUser(UserCreatedEvent userCreatedEvent) {
    	emailCustom.send(userCreatedEvent.getUser().getEmail());
    	log.info("Email sent to new user {}", userCreatedEvent.getUser().getName());	
    }
    
    @EventListener
    public void sendEmailToSalesTeam(UserCreatedEvent userCreatedEvent) {
    	emailCustom.send(salesTeamEmail);
    	log.info("Email sent to sales team user {}", userCreatedEvent.getUser().getName());
    }
    
}