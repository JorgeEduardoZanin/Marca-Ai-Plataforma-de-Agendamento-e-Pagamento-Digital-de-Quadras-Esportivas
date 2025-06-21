package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.port.in.EnterpriseUseCase;
import com.marcaai.core.port.out.external.CheckCnpjRepository;
import com.marcaai.core.port.out.internal.EnterpriseRepository;
import com.marcaai.core.usecase.AddressService;
import com.marcaai.core.usecase.CompanyOwnerService;
import com.marcaai.core.usecase.EnterpriseService;
import com.marcaai.core.usecase.RoleService;

@Configuration
public class EnterpriseConfig {

	@Bean
	public EnterpriseUseCase enterpriseUseCase(CompanyOwnerService companyOwnerService, AddressService addressService,
				RoleService roleService, EnterpriseRepository enterpriseRepository, BCryptPasswordEncoder passwordEncoder, CheckCnpjRepository checkCnpjRepository) {
		
		return new EnterpriseService(companyOwnerService, addressService, roleService, enterpriseRepository, passwordEncoder, checkCnpjRepository);
	}
	
}
