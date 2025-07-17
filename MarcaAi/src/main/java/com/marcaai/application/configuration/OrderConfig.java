package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.in.EnterpriseUseCase;
import com.marcaai.core.port.in.FootballCourtUseCase;
import com.marcaai.core.port.in.OrderUseCase;
import com.marcaai.core.port.in.SchedulingUseCase;
import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.internal.OrderRepository;
import com.marcaai.core.usecase.OrderService;

@Configuration
public class OrderConfig {

	@Bean
    public OrderUseCase orderUseCase(SchedulingUseCase schedulingUseCase, EnterpriseUseCase enterpriseUseCase, FootballCourtUseCase footballCourtUseCase,
    		UserCrudUseCase userCrudUserCase, OrderRepository orderRepository) {
        return new OrderService(schedulingUseCase, enterpriseUseCase, footballCourtUseCase, userCrudUserCase, orderRepository);
    }
	
	
}
