package com.marcaai.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.marcaai.core.port.out.CompanyOwnerRepository;
import com.marcaai.core.usecase.CompanyOwnerService;

@Configuration
public class CompanyOwnerConfig {

	@Bean
	public CompanyOwnerService companyOwnerService(CompanyOwnerRepository companyOwnerRepository) {
		return new CompanyOwnerService(companyOwnerRepository);
	}
	
	
}
