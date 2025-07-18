package com.marcaai.emails.service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class GmailService {

	@Value("${spring.mail.username}")
	private String mailFrom;
	
	private final JavaMailSender javaMailSender;

	public GmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public boolean sendEmail(String to,String verificationCode) {
		
		try {
			
			ClassPathResource resource = new ClassPathResource("html/EmailVerification.html");
			String htmlTemplate = Files.lines(resource.getFile().toPath(), StandardCharsets.UTF_8).collect(Collectors.joining("\n"));
			
			String html = htmlTemplate
	                    .replace("{{userEmail}}", to)
	                    .replace("{{verificationCode}}", verificationCode);
			
		    MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
	        helper.setFrom(mailFrom);
	        helper.setTo(to);
	        helper.setSubject("Verificação de E-mail MarcaAi");
	        helper.setText(html, true); 
			
			
	        javaMailSender.send(message);
	        
	      
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		  
			return true;
	}
	
}
