package com.marcaai.core.port.in;

import java.util.List;
import java.util.UUID;

import com.marcaai.core.domain.Order;

public interface OrderUseCase {

	Order create(List<Long> schedulingsId, UUID enterpriseId, UUID userId);
	
}
