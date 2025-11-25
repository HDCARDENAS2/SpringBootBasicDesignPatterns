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
	

	@Value("${customdata.email.salesTeam}")
	private String salesTeamEmail;
    private final EmailCustom emailCustom;

    private String correo;
    private String body;
    private String bodySales;

    @EventListener
    public void sendEmailToNewUser(UserCreatedEvent userCreatedEvent) {
    	//send a real Email to new user with his id
        log.info("New user {}", userCreatedEvent.getUser().getName());
        log.info("New ID {}", userCreatedEvent.getUser().getId());
        log.info("New Email {}", userCreatedEvent.getUser().getEmail());
        
        correo = userCreatedEvent.getUser().getEmail();
        body = "New user created: " + userCreatedEvent.getUser().getName() + ", ID: " + userCreatedEvent.getUser().getId() + ", Email: " + userCreatedEvent.getUser().getEmail();

        emailCustom.send(correo, body);
    	log.info("Email sent to new user {}", userCreatedEvent.getUser().getName());	
    }
    
    @EventListener
    public void sendEmailToSalesTeam(UserCreatedEvent userCreatedEvent) {
    	//send a real Email to sales team
        bodySales = "New user created: " + userCreatedEvent.getUser().getName() + ", ID: " + userCreatedEvent.getUser().getId() + ", Email: " + userCreatedEvent.getUser().getEmail();
        emailCustom.sendSalesTeam(bodySales);
    	log.info("Email sent to sales team user {}", userCreatedEvent.getUser().getName());
    }
    
}