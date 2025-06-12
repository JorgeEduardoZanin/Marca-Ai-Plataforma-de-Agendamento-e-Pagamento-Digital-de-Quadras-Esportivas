package com.marcaai.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.UserCrudRepository;
import com.marcaai.core.usecase.RoleService;
import com.marcaai.core.usecase.UserCrudService;

@Configuration
public class UserCrudConfig {
	
		@Bean
	    public UserCrudUseCase userCrudUseCase(UserCrudRepository userCrudRepository, BCryptPasswordEncoder passwordEncoder,
	    		RoleService roleService) {
	        return new UserCrudService(roleService, userCrudRepository, passwordEncoder);
	    }

}
