package com.marcaai.adapter.dto.response.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.marcaai.adapter.dto.response.schedulling.SchedullingResponse;
import com.marcaai.core.domain.enums.PaymentStatus;

public record CreateOrderResponse(
		String enterpriseName, 
		String phoneNumber, 
		UUID enterpriseId,
		String address,
		String addressNumber,
		String city,
		String userName,
		long orderId,
		LocalDateTime orderCreateAt,
		PaymentStatus paymentStatus,
		BigDecimal value,
		Optional<String> description,
		Set<SchedullingResponse> schedullingsResponse
		) {

}
