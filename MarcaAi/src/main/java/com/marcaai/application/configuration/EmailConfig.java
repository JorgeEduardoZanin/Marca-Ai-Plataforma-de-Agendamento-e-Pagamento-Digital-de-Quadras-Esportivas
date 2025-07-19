package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.out.external.EmailRepository;
import com.marcaai.core.usecase.EmailService;

@Configuration
public class EmailConfig {

	@Bean
	public EmailService emailSevice(EmailRepository emailRepository) {
		return new EmailService(emailRepository);
	}
	
	
}
