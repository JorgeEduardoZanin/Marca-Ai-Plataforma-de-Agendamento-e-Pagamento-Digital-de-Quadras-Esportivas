package com.marcaai.adapter.mapper;

import com.marcaai.adapter.dto.request.order.OrderRequest;
import com.marcaai.core.domain.Order;

public class OrderMapper {

	public static Order OrderRequestToOrderDomain(OrderRequest order) {
		
		 var orderDomain = new Order(order.value(), order.paymentMethod());
		
		 return orderDomain;
	}
	
}
