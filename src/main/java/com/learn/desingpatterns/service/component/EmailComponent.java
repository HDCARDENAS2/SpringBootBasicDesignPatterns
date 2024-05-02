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
@RequiredArgsConstructor
@Log4j2
public class EmailComponent {
	
    private final String username;
    private final String password;
    private final Properties props;
    
    public EmailComponent() {
        // Aquí podrías cargar las credenciales desde una configuración externa, una base de datos, etc.
        this.username = "prueba@gmail.com";
        this.password = "";

         props = new Properties();
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", "587");
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.starttls.enable", "true");

    }
	

	@Value("${customdata.email.salesTeam}")
	private String salesTeamEmail;
	
    @EventListener
    public void sendEmailToNewUser(UserCreatedEvent userCreatedEvent) throws MessagingException {
    	sendEmail(userCreatedEvent.getUser().getEmail(),"User creado","user registrado"+userCreatedEvent.getUser().getId());
    	log.info("Email sent to new user {}", userCreatedEvent.getUser().getName());	
    }
    
    @EventListener
    public void sendEmailToSalesTeam(UserCreatedEvent userCreatedEvent) throws MessagingException {
    	sendEmail(salesTeamEmail,"user registrado","user registrado"+userCreatedEvent.getUser().getName());
    	log.info("Email sent to sales team user {}", userCreatedEvent.getUser().getName());
    }
    
    
    private void sendEmail(String recipient, String subject, String body) throws MessagingException {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
    
}