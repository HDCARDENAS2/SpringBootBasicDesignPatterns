package com.learn.desingpatterns.service.component;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.learn.desingpatterns.custom.EmailCustom;
import com.learn.desingpatterns.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component

@Log4j2
public class EmailComponent {
	

    
    private final  EmailCustom emailCustom;
    
    public EmailComponent(EmailCustom emailCustom ) {
    this.emailCustom=emailCustom;
    }
	

	@Value("${customdata.email.salesTeam}")
	private String salesTeamEmail;
	
    @EventListener
    public void sendEmailToNewUser(UserCreatedEvent userCreatedEvent) throws MessagingException {
    	emailCustom.sendEmail(userCreatedEvent.getUser().getEmail(),"User creado","user registrado"+userCreatedEvent.getUser().getId());
        log.info("Email sent to new user {}", userCreatedEvent.getUser().getName());	
    }
    
    @EventListener
    public void sendEmailToSalesTeam(UserCreatedEvent userCreatedEvent) throws MessagingException {
    	emailCustom.sendEmail(salesTeamEmail,"user registrado","user registrado"+userCreatedEvent.getUser().getName());
    	log.info("Email sent to sales team user {}", userCreatedEvent.getUser().getName());
    }
    
    

    
}