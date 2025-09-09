package com.marcaai.adapter.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.marcaai.adapter.dto.request.order.OrderRequest;
import com.marcaai.adapter.dto.response.order.CreateOrderResponse;
import com.marcaai.adapter.dto.response.schedulling.SchedullingResponse;
import com.marcaai.adapter.out.database.entity.EnterpriseEntity;
import com.marcaai.adapter.out.database.entity.OrderEntity;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.core.domain.Order;
import com.marcaai.core.domain.Schedulling;
import com.marcaai.core.domain.enums.PaymentStatus;

public class OrderMapper {
	
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
		orderDomain.setCreatedAt(orderEntity.getCreatedAt());
	
		orderDomain.setStatus(orderEntity.getStatus());
		orderDomain.setValue(orderEntity.getValue().setScale(2, RoundingMode.HALF_UP));
		Optional.ofNullable(orderEntity.getDescription()).ifPresent(orderDomain::setDescription);
		
		return orderDomain;
	}
	
	public static CreateOrderResponse orderDomainToOrderResponse(Order order) {
		
		Set<Schedulling> schedulingResponse = new HashSet<>(order.getSchedulings());
		return new CreateOrderResponse(
				order.getEnterprise().getFantasyName(),
				order.getEnterprise().getPhoneNumber(),
				order.getEnterprise().getId(),
				order.getEnterprise().getAddress().getAdress(),
				order.getEnterprise().getAddress().getAdress_number(),
				order.getEnterprise().getAddress().getCity(),
				order.getUser().getName(),
				order.getId(),
				order.getCreatedAt(),
				order.getStatus(),
				order.getValue(),
				Optional.ofNullable(order.getDescription()),
				SchedullingMapper.listSchedullingDomainToListSchedullingResponse(schedulingResponse));
		
	}

}	
