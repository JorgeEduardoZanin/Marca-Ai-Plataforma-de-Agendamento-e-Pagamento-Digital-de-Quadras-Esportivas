package com.marcaai.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.in.EnterpriseUseCase;
import com.marcaai.core.usecase.AddressService;
import com.marcaai.core.usecase.CompanyOwnerService;
import com.marcaai.core.usecase.EnterpriseService;

@Configuration
public class EnterpriseConfig {

	@Bean
	public EnterpriseUseCase enterpriseUseCase(CompanyOwnerService companyOwnerService, AddressService addressService) {
		return new EnterpriseService(companyOwnerService, addressService);
	}
	
}
