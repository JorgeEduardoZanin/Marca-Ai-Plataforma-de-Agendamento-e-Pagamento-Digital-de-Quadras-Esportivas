package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.in.FootballCourtUseCase;
import com.marcaai.core.port.out.internal.FootballCourtRepository;
import com.marcaai.core.usecase.FootballCourtService;

@Configuration
public class FootballCourtConfig {

	@Bean
	public FootballCourtUseCase footballCourtUseCase(FootballCourtRepository footballCourtRepository) {
		return new FootballCourtService(footballCourtRepository);
	}
	
}
