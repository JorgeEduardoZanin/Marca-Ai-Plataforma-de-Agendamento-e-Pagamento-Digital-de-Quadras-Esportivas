package com.marcaai.adapter.dto.request.order;

import java.math.BigDecimal;
import java.util.List;

import com.marcaai.core.domain.enums.PaymentMethod;

import jakarta.validation.constraints.NotEmpty;

public record OrderRequest(
		@NotEmpty List<Long> schedulingsId,
		@NotEmpty PaymentMethod paymentMethod,
		@NotEmpty BigDecimal value, 
		String description) {

}
