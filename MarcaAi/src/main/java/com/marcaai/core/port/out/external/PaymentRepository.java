package com.marcaai.core.port.out.external;

import com.marcaai.core.domain.Payment;

public interface PaymentRepository {

	Payment creditPayment(Payment payment);
	
	Payment pixPayment(Payment payment);
	
}
