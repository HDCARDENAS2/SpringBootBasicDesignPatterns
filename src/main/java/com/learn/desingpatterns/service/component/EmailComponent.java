package com.learn.desingpatterns.service.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.learn.desingpatterns.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class EmailComponent {
	
    private final JavaMailSender emailSender;

	@Value("${customdata.email.salesTeam}")
	private String salesTeamEmail;
	@Value("${spring.mail.username}")
	private String fromEmail;
	
    @EventListener
    public void sendEmailToNewUser(UserCreatedEvent userCreatedEvent) {

    	SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(fromEmail);
        message.setTo(userCreatedEvent.getUser().getEmail()); 
        message.setSubject("wellcome"); 
        message.setText(String.format("hello! user %s ", userCreatedEvent.getUser().getName()));
        emailSender.send(message);
    	
    	log.info("Email sent to new user {}", userCreatedEvent.getUser().getName());	
    }
    
    @EventListener
    public void sendEmailToSalesTeam(UserCreatedEvent userCreatedEvent) {
    	
    	SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(fromEmail);
        message.setTo(salesTeamEmail); 
        message.setSubject("new user created"); 
        message.setText(String.format("new user %s ", userCreatedEvent.getUser().getName()));
        emailSender.send(message);
    	log.info("Email sent to sales team user {}", userCreatedEvent.getUser().getName());
    }
    
}