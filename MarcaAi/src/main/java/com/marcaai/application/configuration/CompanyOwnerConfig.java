package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.in.CompanyOwnerUseCase;
import com.marcaai.core.port.out.internal.CompanyOwnerRepository;
import com.marcaai.core.usecase.CompanyOwnerService;

@Configuration
public class CompanyOwnerConfig {

	@Bean
	public CompanyOwnerUseCase companyOwnerUseCase(CompanyOwnerRepository companyOwnerRepository) {
		return new CompanyOwnerService(companyOwnerRepository);
	}
	
	
}
