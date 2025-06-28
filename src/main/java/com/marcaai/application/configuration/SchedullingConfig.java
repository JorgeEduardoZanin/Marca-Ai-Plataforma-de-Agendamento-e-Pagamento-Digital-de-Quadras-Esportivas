package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.in.SchedullingUseCase;
import com.marcaai.core.port.out.internal.SchedullingRepository;
import com.marcaai.core.usecase.FootballCourtService;
import com.marcaai.core.usecase.SchedullingService;

@Configuration
public class SchedullingConfig {

	@Bean
	public SchedullingUseCase schedullingUseCase(SchedullingRepository schedullingRepository, FootballCourtService footballCourtService) {
		return new SchedullingService(schedullingRepository, footballCourtService);
	}
}
