package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.internal.UserCrudRepository;
import com.marcaai.core.usecase.AddressService;
import com.marcaai.core.usecase.RoleService;
import com.marcaai.core.usecase.UserCrudService;

@Configuration
public class UserCrudConfig {
	
		@Bean
	    public UserCrudUseCase userCrudUseCase(UserCrudRepository userCrudRepository, BCryptPasswordEncoder passwordEncoder,
	    		RoleService roleService, AddressService addressService) {
	        return new UserCrudService(roleService, userCrudRepository, passwordEncoder, addressService);
	    }

}
