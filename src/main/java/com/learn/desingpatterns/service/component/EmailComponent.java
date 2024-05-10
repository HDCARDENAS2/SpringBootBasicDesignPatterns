package com.learn.desingpatterns.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @EventListener
    public void sendEmailToNewUser(UserCreatedEvent userCreatedEvent) {
        //TODO send a real Email to user
        sendSimpleMessage(userCreatedEvent.getUser().getEmail(), "Mentoría", userCreatedEvent.getUser().getName());
        log.info("Email sent to new user {}", userCreatedEvent.getUser().getName());
    }

    @EventListener
    public void sendEmailToSalesTeam(UserCreatedEvent userCreatedEvent) {
        //TODO send a real Email to sales team
        sendSimpleMessage(salesTeamEmail, "Mentoría", userCreatedEvent.getUser().getName());
        log.info("Email sent to sales team user {}", userCreatedEvent.getUser().getName());
    }

}