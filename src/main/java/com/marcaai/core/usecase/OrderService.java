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
import com.marcaai.core.port.in.OrderUseCase;
import com.marcaai.core.port.out.internal.OrderRepository;
import com.marcaai.core.usecase.utils.ValidateId;

public class OrderService implements OrderUseCase{

	private final SchedullingService schedulingService;
	private final EnterpriseService enterpriseService;
	private final FootballCourtService footballCourtService;
	private final UserCrudService userCrudService;
	private final OrderRepository orderRepository;

	public OrderService(SchedullingService schedulingService, EnterpriseService enterpriseService,
			FootballCourtService footballCourtService, UserCrudService userCrudService,
			OrderRepository orderRepository) {
		this.schedulingService = schedulingService;
		this.enterpriseService = enterpriseService;
		this.footballCourtService = footballCourtService;
		this.userCrudService = userCrudService;
		this.orderRepository = orderRepository;
	}

	@Override
	public Order create(List<Long> schedulingsId, Order order, UUID enterpriseId, UUID userId) {
		
		ValidateId.validateUUIDId(userId);
		ValidateId.validateUUIDId(enterpriseId);
		
		var enterprise = enterpriseService.findById(userId);
		var schedulingsDomain = schedulingService.findAllByIds(schedulingsId);
		var user = userCrudService.getUserById(userId);
		
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
		
		var footballCourts = footballCourtService.findAllByIds(footballCourtsIds);
		
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
		
		schedulingService.updateReservationsAndOrders(order.getId(), schedulingsId);
		
		return order;
	}

	
}
