package com.marcaai.emails.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class GmailService {

	@Value("${spring.mail.username}")
	private String mailFrom;
	
	private final JavaMailSender javaMailSender;

	public GmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(String to, String subject, String bodyText) {
		
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(mailFrom);
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(bodyText);
		
			javaMailSender.send(simpleMailMessage);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
