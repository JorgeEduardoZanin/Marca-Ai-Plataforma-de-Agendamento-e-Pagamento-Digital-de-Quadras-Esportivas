package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.in.EmailUseCase;
import com.marcaai.core.port.out.external.EmailRepository;
import com.marcaai.core.usecase.EmailService;
import com.marcaai.core.usecase.EnterpriseService;
import com.marcaai.core.usecase.UserCrudService;

@Configuration
public class EmailConfig {

	@Bean
	public EmailUseCase emailSevice(EmailRepository emailRepository, EnterpriseService enterpriseService, UserCrudService userCrudService) {
		return new EmailService(emailRepository, enterpriseService, userCrudService);
	}
	
	
}
