package com.marcaai.adapter.mapper;

import com.marcaai.core.domain.Order;
import com.marcaai.core.domain.Payment;
import com.marcaai.core.domain.enums.PaymentStatus;
import com.marcaai.core.usecase.payment.dto.request.client.ClientRequest;
import com.marcaai.core.usecase.payment.dto.request.pix.PaymentPixRequest;
import com.marcaai.core.usecase.payment.dto.response.client.ClientResponse;
import com.marcaai.core.usecase.payment.dto.response.pix.PaymentPixResponse;

public class PaymentMapper {

	public static Payment paymentClientResponseToPaymentDomain(ClientResponse clientResponse) {
		var domain = new Payment(clientResponse.name(), clientResponse.email(), clientResponse.cellphone(), clientResponse.cpf_cnpj());
		domain.setId(clientResponse.clientPaymentId());
		return domain;
			
	}
	
	public static ClientRequest paymentDomainToPaymentClientRequest(Payment payment) {
		return new ClientRequest(payment.getName(), payment.getPhoneNumber(), payment.getEmail(), payment.getCpf());
	}
	
	public static PaymentPixRequest paymentDomainToPaymentPixRequest(Payment payment, int expiresIn) {
		return new PaymentPixRequest(
				payment.getAmount(),
				expiresIn,
				payment.getName(),
				payment.getEmail(),
				payment.getCpf(),
				payment.getPhoneNumber());
	}
	
	public static Payment paymentPixResponseToPaymentDomain(PaymentPixResponse payment) {
		var domain = new Payment();
		domain.setAmount(payment.amount());
		domain.setPixCode(payment.pixCode());
		domain.setPixCodeBase64(payment.pixCodeBase64());
		domain.setExipiresAt(payment.expiresAt());
		domain.setCreateAt(payment.createAt());
		domain.setPaymentStatus(PaymentStatus.valueOf(payment.status()));
		
		return domain;
		
	}
	
	public static Payment createPaymentThroughOrder(Order order) {
		
		var domain = new Payment();
		domain.setAmount(order.getValue());
		domain.setCpf(order.getUser().getCpf());
		domain.setPhoneNumber(order.getUser().getPhone_number());
		domain.setEmail(order.getUser().getEmail());
		domain.setName(order.getUser().getName());
		
		return domain;
		
	}
}




