package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.out.external.PaymentRepository;
import com.marcaai.core.usecase.payment.PaymentService;

@Configuration
public class PaymentConfig {

	@Bean
	public PaymentService paymentUseCase(PaymentRepository paymentRepository) {
		return new PaymentService(paymentRepository);
	}
	
}
