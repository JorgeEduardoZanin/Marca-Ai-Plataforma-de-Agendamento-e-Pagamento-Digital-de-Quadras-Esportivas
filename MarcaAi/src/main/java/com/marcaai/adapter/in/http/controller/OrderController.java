package com.marcaai.adapter.in.http.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.order.OrderRequest;
import com.marcaai.adapter.dto.response.order.OrderResponse;
import com.marcaai.adapter.mapper.OrderMapper;
import com.marcaai.core.port.in.OrderUseCase;
import com.rabbitmq.client.AMQP.Basic.Return;

@RestController
@RequestMapping("/enterprise/potato")
public class OrderController {

	private final OrderUseCase orderUseCase;

	public OrderController(OrderUseCase orderUseCase) {
		this.orderUseCase = orderUseCase;
	}

	@PostMapping
	public ResponseEntity<OrderResponse> create(@PathVariable UUID enterpriseId, @RequestBody OrderRequest order, JwtAuthenticationToken token){
		System.out.println(UUID.fromString(token.getName()));
		var orders = orderUseCase.create(order.schedulingsId(),OrderMapper.OrderRequestToOrderDomain(order), enterpriseId, UUID.fromString(token.getName())); 
		
		return ResponseEntity.ok(OrderMapper.orderDomainToOrderResponse(orders));
		
	}
	
	@GetMapping
	public ResponseEntity<String> rete(JwtAuthenticationToken token){
	System.out.println(UUID.fromString(token.getName()));
	
	return ResponseEntity.ok("teste");
		
	}
	
}
