package com.marcaai.adapter.mapper;

import java.math.RoundingMode;
import java.util.Optional;

import com.marcaai.adapter.dto.request.order.OrderRequest;
import com.marcaai.adapter.dto.response.order.OrderResponse;
import com.marcaai.adapter.out.database.entity.EnterpriseEntity;
import com.marcaai.adapter.out.database.entity.OrderEntity;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.core.domain.Order;
import com.marcaai.core.domain.enums.PaymentStatus;

public class OrderMapper {

	public static Order OrderRequestToOrderDomain(OrderRequest order) {
		 var orderDomain = new Order(order.value().setScale(2, RoundingMode.HALF_UP), order.paymentMethod());
		 return orderDomain;
	}
	
	public static OrderEntity orderDomainToOrderEntity(Order order) {
		
		EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
		enterpriseEntity.setId(order.getEnterprise().getId());
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(order.getUser().getId());
		
		OrderEntity orderEntity = new OrderEntity(enterpriseEntity, userEntity, order.getValue(), PaymentStatus.PENDING);
		Optional.ofNullable(order.getDescription()).ifPresent(orderEntity::setDescription);
		
		return orderEntity;
	
	}
	
	public static Order orderEntityToOrderDomain(OrderEntity orderEntity) {
			
		var orderDomain = new Order();
		orderDomain.setId(orderEntity.getId());
		orderDomain.setEnterprise(EnterpriseMapper.enterpriseEntityToEnterpriseDomain(orderEntity.getEnterpriseEntity()));
		orderDomain.setUser(UserMapper.UserEntityToUserDomain(orderEntity.getUserEntity()));
		orderDomain.setPaymentMethod(orderEntity.getPaymentMethod());
		orderDomain.setStatus(orderEntity.getStatus());
		orderDomain.setValue(orderEntity.getValue().setScale(2, RoundingMode.HALF_UP));
		Optional.ofNullable(orderEntity.getDescription()).ifPresent(orderDomain::setDescription);
		
		return orderDomain;
	}
	
	public static OrderResponse orderDomainToOrderResponse(Order order) {
		return new OrderResponse(
				order.getId(),
				EnterpriseMapper.enterpriseDomainToEnterpriseResponse(order.getEnterprise()),
				UserMapper.UserCrudToUserCrudResponse(order.getUser()),
				order.getPaymentMethod(),
				order.getStatus(),
				order.getValue(),
				null);
		
	}
	
}	
