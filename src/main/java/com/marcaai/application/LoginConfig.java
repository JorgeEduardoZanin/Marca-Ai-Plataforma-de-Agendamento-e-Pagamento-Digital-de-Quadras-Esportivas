package com.marcaai.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import com.marcaai.core.port.in.LoginUseCase;
import com.marcaai.core.port.out.LoginRepository;
import com.marcaai.core.usecase.LoginService;


@Configuration
public class LoginConfig {

	@Bean
    public LoginUseCase loginUseCase(LoginRepository loginRepository,JwtEncoder jwtEncoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new LoginService(loginRepository, jwtEncoder, bCryptPasswordEncoder);
    }
	
}
