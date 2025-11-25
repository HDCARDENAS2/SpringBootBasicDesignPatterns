package com.learn.desingpatterns.custom;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public class EmailCustom {

	@Value("${customdata.email.salesTeam}")
	private String endPoint;
	private JavaMailSender emailSender;
	
	
	/*public EmailCustom(String endPoint) {
		super();
		this.endPoint = endPoint;
	}*/

	public EmailCustom(String endPoint, JavaMailSender emailSender) {
        this.endPoint = endPoint;
		this.emailSender = emailSender;
    }

	public void send(String email, String  body) {
        SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Notificacion de Bienvenida");
		message.setText(body);

		// Here you would add the logic to send the email using an email service provider.
		emailSender.send(message);

		log.info("EmailCustom service sending email to {} with endPoint {} ", email, endPoint);
	}

	public void sendSalesTeam(String  body) {
        SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(endPoint);
		message.setSubject("Notificacion de Bienvenida");
		message.setText(body);

		// Here you would add the logic to send the email using an email service provider.
		emailSender.send(message);

		log.info("EmailCustom service sending email to {} with endPoint {} ", endPoint);
	}
	
}
