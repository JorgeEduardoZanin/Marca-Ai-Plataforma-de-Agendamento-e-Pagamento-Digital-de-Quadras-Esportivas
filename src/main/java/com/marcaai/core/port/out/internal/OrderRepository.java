package com.marcaai.core.port.out.internal;

import com.marcaai.core.domain.Order;

public interface OrderRepository {

	Order createOrder(Order order);
	
	
}
