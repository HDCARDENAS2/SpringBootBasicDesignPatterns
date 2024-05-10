package com.learn.desingpatterns.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.learn.desingpatterns.event.UserCreatedEvent;
import com.learn.desingpatterns.service.impl.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class EmailComponent {
	

	@Value("${customdata.email.salesTeam}")
	private String salesTeamEmail;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Autowired
	private EmailService emailService;
	
    @EventListener
    public void sendEmailToNewUser(UserCreatedEvent userCreatedEvent) {
    	//TODO send a real Email to user
    	String mensaje = "ID nuevo usuario: " + userCreatedEvent.getUser().getId();
    	String asunto = "Pruebas Email";
    	log.info("Email sent to new user {}", userCreatedEvent.getUser().getName());
    	emailService.sendMail(fromEmail, fromEmail, asunto, mensaje);
    }
    
    @EventListener
    public void sendEmailToSalesTeam(UserCreatedEvent userCreatedEvent) {
    	//TODO send a real Email to sales team
    	String mensaje = "Nombre nuevo usuario: " + userCreatedEvent.getUser().getName();
    	String asunto = "Sergio Cano: Nuevo susuario creado.";
    	log.info("Email sent to new user {}", userCreatedEvent.getUser().getName());
    	emailService.sendMail(fromEmail, salesTeamEmail, asunto, mensaje);
    	log.info("Email sent to sales team user {}", userCreatedEvent.getUser().getName());
    }
    
}