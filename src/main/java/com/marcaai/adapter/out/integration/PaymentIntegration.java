package com.marcaai.adapter.out.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.marcaai.adapter.mapper.PaymentMapper;
import com.marcaai.core.domain.Payment;
import com.marcaai.core.port.out.external.PaymentRepository;
import com.marcaai.core.usecase.payment.dto.response.pix.PaymentPixResponse;

@Component
public class PaymentIntegration implements PaymentRepository{

	@Value("${payment.api}")
	private String API_PAYMENT_PIX;
	
	private final PaymentClientIntegration paymentClientIntegration;
	
	private final WebClient webClient;

	public PaymentIntegration(PaymentClientIntegration paymentClientIntegration, WebClient webClient) {
		this.paymentClientIntegration = paymentClientIntegration;
		this.webClient = webClient;
	}

	@Override
	public Payment creditPayment(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment pixPayment(Payment payment) {

		return PaymentMapper.paymentPixResponseToPaymentDomain(webClient.post()
				.uri("https://api.abacatepay.com/v1/pixQrCode/create")
				.header("Authorization", "Bearer "+API_PAYMENT_PIX)
				.header("Content-Type", "application/json")
				.bodyValue(PaymentMapper.paymentDomainToPaymentPixRequest(payment, 300))
				.retrieve()
				.bodyToMono(PaymentPixResponse.class)
				.block());

	}

}
