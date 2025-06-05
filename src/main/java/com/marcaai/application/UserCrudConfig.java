package com.marcaai.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.UserCrudRepository;
import com.marcaai.core.usecase.UserCrudService;

@Configuration
public class UserCrudConfig {
	
	  @Bean
	    public UserCrudUseCase userCrudUseCase(UserCrudRepository userCrudRepository) {
	        return new UserCrudService(userCrudRepository);
	    }

}
