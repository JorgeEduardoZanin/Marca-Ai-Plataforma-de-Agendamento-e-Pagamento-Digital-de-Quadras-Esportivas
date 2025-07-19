package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.internal.UserCrudRepository;
import com.marcaai.core.usecase.AddressService;
import com.marcaai.core.usecase.EmailService;
import com.marcaai.core.usecase.RoleService;
import com.marcaai.core.usecase.UserCrudService;

@Configuration
public class UserCrudConfig {
	
		@Bean
	    public UserCrudUseCase userCrudUseCase(AddressService addressService, RoleService roleService,
				UserCrudRepository userCrudRepository, BCryptPasswordEncoder passwordEncoder, EmailService emailService) {
	        return new UserCrudService(addressService, roleService, userCrudRepository, passwordEncoder, emailService);
	    }

}
