package com.marcaai.core.usecase;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.Order;
import com.marcaai.core.domain.Schedulling;
import com.marcaai.core.port.in.EnterpriseUseCase;
import com.marcaai.core.port.in.FootballCourtUseCase;
import com.marcaai.core.port.in.OrderUseCase;
import com.marcaai.core.port.in.SchedulingUseCase;
import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.internal.OrderRepository;
import com.marcaai.core.usecase.utils.ValidateId;

public class OrderService implements OrderUseCase{

	private final SchedulingUseCase schedulingUseCase;
	private final EnterpriseUseCase enterpriseUseCase;
	private final FootballCourtUseCase footballCourtUseCase;
	private final UserCrudUseCase userCrudUseCase;
	private final OrderRepository orderRepository;

	public OrderService(SchedulingUseCase schedulingUseCase, EnterpriseUseCase enterpriseUseCase,
			FootballCourtUseCase footballCourtUseCase, UserCrudUseCase userCrudUseCase,
			OrderRepository orderRepository) {
		this.schedulingUseCase = schedulingUseCase;
		this.enterpriseUseCase = enterpriseUseCase;
		this.footballCourtUseCase = footballCourtUseCase;
		this.userCrudUseCase = userCrudUseCase;
		this.orderRepository = orderRepository;
	}

	@Override
	public Order create(List<Long> schedulingsId, Order order, UUID enterpriseId, UUID userId) {
		
		ValidateId.validateUUIDId(userId);
		ValidateId.validateUUIDId(enterpriseId);
		
		var enterprise = enterpriseUseCase.findById(userId);
		var schedulingsDomain = schedulingUseCase.findAllByIds(schedulingsId);
		var user = userCrudUseCase.getUserById(userId);
		
		for (Schedulling scheduling : schedulingsDomain) {
			scheduling.setOrder(order);
		}
		
		order.setSchedulings(schedulingsDomain);
		order.setEnterprise(enterprise.enterprise());
		order.setUser(user.user());
		
		Set<Long> footballCourtsIds = new HashSet<>();
		
		for (Schedulling scheduling : schedulingsDomain) {
			footballCourtsIds.add(scheduling.getFootballCourt().getId());
		}
		
		var footballCourts = footballCourtUseCase.findAllByIds(footballCourtsIds);
		
		//joga o id como chave e o preco da quadra como valor
		Map<Long, BigDecimal> priceMap = footballCourts.stream()
		    .collect(Collectors.toMap(FootballCourt::getId, FootballCourt::getValue));

		
		//filtra se o agendamento esta reservado
		//usando map pega a chave do id correspondete ao agendamento
		//pega o valor da chave e soma ao total
		BigDecimal totalValue = schedulingsDomain.stream()
			.filter(s -> !s.getReserved())
		    .map(s -> priceMap.get(s.getFootballCourt().getId()))
		    .filter(Objects::nonNull)
		    .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		order.setValue(totalValue);
		
		if(!totalValue.equals(order.getValue())) {
			System.out.println("erro");
		}
		
		order = orderRepository.createOrder(order);
		
		Set<Long> schedulingsIds = new HashSet<>();
		
		for (Schedulling schedulling: schedulingsDomain) {
			schedulingsIds.add(schedulling.getId());
		}
		
		schedulingUseCase.updateReservationsAndOrders(order.getId(), schedulingsId);
		
		return order;
	}

	
}
