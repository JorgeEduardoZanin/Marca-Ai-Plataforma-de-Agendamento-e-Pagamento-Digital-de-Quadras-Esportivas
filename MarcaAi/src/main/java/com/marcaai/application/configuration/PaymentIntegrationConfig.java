package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.marcaai.adapter.out.integration.PaymentClientIntegration;
import com.marcaai.adapter.out.integration.PaymentIntegration;

@Configuration
public class PaymentIntegrationConfig {

	 @Bean
	public WebClient webClient() {
		 return WebClient.builder().build();
	}
	
	@Bean
	public PaymentIntegration paymentIntegration(PaymentClientIntegration paymentClientIntegration, WebClient webClient) {
		return new PaymentIntegration(paymentClientIntegration, webClient);
	}
	
}
