package com.marcaai.adapter.out.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.marcaai.adapter.mapper.PaymentMapper;
import com.marcaai.core.domain.Payment;
import com.marcaai.core.usecase.payment.dto.request.client.ClientRequest;
import com.marcaai.core.usecase.payment.dto.response.client.ClientResponse;


@Component
public class PaymentClientIntegration {

	@Value("${payment.api}")
	private String API_PAYMENT;
	
	private final WebClient webClient;
	
	public PaymentClientIntegration(WebClient webClient) {
		this.webClient = webClient;
	}

	public Payment createPaymentClientAbacate(ClientRequest clientRequest) {
		return PaymentMapper.paymentClientResponseToPaymentDomain(webClient.post()
				.uri("https://api.abacatepay.com/v1/customer/create")
				.header("Authorization", "Bearer "+API_PAYMENT)
				.header("Content-Type", "application/json")
				.bodyValue(clientRequest)
				.retrieve()
				.bodyToMono(ClientResponse.class)
				.block());
	}
}
