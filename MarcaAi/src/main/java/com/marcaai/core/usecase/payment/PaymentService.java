package com.marcaai.core.usecase.payment;

import java.util.Map;
import java.util.function.Function;
import java.util.EnumMap;

import com.marcaai.core.domain.Payment;
import com.marcaai.core.domain.enums.PaymentMethod;
import com.marcaai.core.port.out.external.PaymentRepository;

public class PaymentService {



	private final PaymentRepository paymentRepository;
	
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public Payment createPayment(Payment payment) {
		Map<PaymentMethod,  Function<Payment, Payment>> strategy = new EnumMap<>(Map.of(
				PaymentMethod.CREDIT, paymentRepository::creditPayment,
				PaymentMethod.PIX, paymentRepository::pixPayment
				));
		
		return strategy.get(payment.getPaymentMethod()).apply(payment);
		
	}	    
	
}
