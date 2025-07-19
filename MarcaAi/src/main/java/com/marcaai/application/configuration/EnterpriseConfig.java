package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.port.in.CompanyOwnerUseCase;
import com.marcaai.core.port.in.EnterpriseUseCase;
import com.marcaai.core.port.out.external.CheckCnpjRepository;
import com.marcaai.core.port.out.internal.EnterpriseRepository;
import com.marcaai.core.usecase.AddressService;
import com.marcaai.core.usecase.EmailService;
import com.marcaai.core.usecase.EnterpriseService;
import com.marcaai.core.usecase.RoleService;

@Configuration
public class EnterpriseConfig {

	@Bean
	public EnterpriseUseCase enterpriseUseCase(CompanyOwnerUseCase companyOwnerUseCase, AddressService addressService,
			RoleService roleService, EnterpriseRepository enterpriseRepository, BCryptPasswordEncoder passwordEncoder,
			CheckCnpjRepository checkCnpjRepository, EmailService emailService) {
		
		return new EnterpriseService(companyOwnerUseCase, addressService, roleService, enterpriseRepository, passwordEncoder, checkCnpjRepository, emailService);
	}
	
}
