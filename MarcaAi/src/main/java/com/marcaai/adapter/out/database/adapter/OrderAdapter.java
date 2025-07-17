package com.marcaai.adapter.out.database.adapter;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.OrderMapper;
import com.marcaai.adapter.out.database.repository.OrderDatabaseRepository;
import com.marcaai.core.domain.Order;
import com.marcaai.core.port.out.internal.OrderRepository;

@Component
public class OrderAdapter implements OrderRepository{

	private final OrderDatabaseRepository orderDatabaseRepository;
	
	public OrderAdapter(OrderDatabaseRepository orderDatabaseRepository) {
		this.orderDatabaseRepository = orderDatabaseRepository;
	}

	@Override
	public Order createOrder(Order order) {
		
		var orderEntity = orderDatabaseRepository.save(OrderMapper.orderDomainToOrderEntity(order));
		
		return OrderMapper.orderEntityToOrderDomain(orderEntity);
	}

}
