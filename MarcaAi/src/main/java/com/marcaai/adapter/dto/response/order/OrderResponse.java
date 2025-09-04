package com.marcaai.adapter.dto.response.order;

import java.math.BigDecimal;
import java.util.Optional;

import com.marcaai.adapter.dto.response.enterprise.EnterpriseResponse;
import com.marcaai.adapter.dto.response.usercrud.UserCrudResponse;
import com.marcaai.core.domain.enums.PaymentMethod;
import com.marcaai.core.domain.enums.PaymentStatus;

public record OrderResponse(
		Long id,
		EnterpriseResponse enterpriseResponse,
		UserCrudResponse userResponse,
		PaymentStatus paymentStatus,
		BigDecimal value,
		Optional<String> description) {

}
