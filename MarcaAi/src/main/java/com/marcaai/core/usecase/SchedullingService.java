package com.marcaai.core.usecase;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


import com.marcaai.core.domain.Schedulling;
import com.marcaai.core.exception.SchedulingException;
import com.marcaai.core.exception.enums.ExceptionSchedulingType;
import com.marcaai.core.port.in.SchedulingUseCase;
import com.marcaai.core.port.out.internal.SchedullingRepository;
import com.marcaai.core.usecase.utils.ValidateId;

public class SchedullingService implements SchedulingUseCase {

	private final SchedullingRepository schedullingRepository;

	private final FootballCourtService footballCourtService;

	public SchedullingService(SchedullingRepository schedullingRepository, FootballCourtService footballCourtService) {
		this.schedullingRepository = schedullingRepository;
		this.footballCourtService = footballCourtService;
	}

	@Override
	public Set<Schedulling> create(Set<Schedulling> schedullings, long footballCourtId, UUID enterpriseId) {
		
		if(schedullings.isEmpty()) {
			throw new SchedulingException(ExceptionSchedulingType.REQUIRE_AT_LEAST_ONE_SCHEDULE , null);
		}
		
		List<String> overlappingSchedules = new ArrayList<>();
		
		ValidateId.validateLongId(footballCourtId);
		
		var footballCourt = footballCourtService.findById(footballCourtId, enterpriseId);
		
		schedullings.forEach(s -> {
			s.setFootballCourt(footballCourt);
			s.setEndTime(s.getStartTime().plusHours(s.getDuration()));
		});
		
		schedullings = schedullings.stream()
		.filter(s -> s.getStartTime().isBefore(s.getEndTime()))
		.sorted(Comparator.comparing(Schedulling::getStartTime))
		.collect(Collectors.toCollection(LinkedHashSet::new));
		
		List<Schedulling> schedullingsList = new ArrayList<>(schedullings);
		
	
		var databaseSchedullingsList = schedullingRepository.findAllByFootballCourtAndDate(footballCourtId, schedullingsList.get(0).getStartTime().toLocalDate(),
				schedullingsList.get(schedullingsList.size()-1).getStartTime().toLocalDate());
			
		if(databaseSchedullingsList != null && !databaseSchedullingsList.isEmpty()) {
		
			databaseSchedullingsList = databaseSchedullingsList.stream()
				.filter(date -> date.getEndTime().isAfter(date.getStartTime()))
				.toList();			
			
			validateNoOverlapWithDatabaseSchedules(databaseSchedullingsList, schedullingsList, overlappingSchedules);
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		for(Schedulling sched : schedullingsList) {
			for (DayOfWeek closedDays : footballCourt.getClosedDay()) {
				System.out.println(closedDays);
				if(sched.getStartTime().getDayOfWeek() == closedDays) {
					
					stringBuilder.append(sched.getStartTime()).append(" está agendado para o dia da semana ")
					.append(closedDays).append(", o qual está fechado.");
					
					overlappingSchedules.add(stringBuilder.toString());
					stringBuilder.setLength(0);
				}
			}
		}
		
		if(!overlappingSchedules.isEmpty()) {
			throw new SchedulingException(ExceptionSchedulingType.CANNOT_SCHEDULE_ON_CLOSED_DAY, overlappingSchedules);
		}
		
		if (schedullingsList.size() > 1) {	
		validateNoOverlapWithinRequest(schedullingsList, overlappingSchedules);
		}
		
		schedullingsList=schedullingRepository.create(schedullingsList);

		return schedullingsList.stream()
			.sorted(Comparator.comparing(Schedulling::getStartTime))
			.collect(Collectors.toCollection(LinkedHashSet::new));

	}

	@Override
	public Schedulling findById(long id, long footballCourtId, UUID enterpriseId) {
		validateSchedulingOwnership(footballCourtId, id);
		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		return schedullingRepository.findById(id);
	}

	@Override
	public Set<Schedulling> findAllByFootballCourtAndDay(long footballCourtId, LocalDate date, UUID enterpriseId) {

		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		
		var schedullingsByFootballCourt = schedullingRepository.findAllByFootballCourtAndDay(footballCourtId, date).stream()
				.sorted(Comparator.comparing(Schedulling::getStartTime))
				.collect(Collectors.toSet());
			
		return schedullingsByFootballCourt;
				
	}

	@Override
	public Schedulling update(Schedulling schedulling, long id) {
		
		
		ValidateId.validateLongId(id);
		
		schedulling.setId(id);
		
		return schedullingRepository.update(schedulling);
	}

	@Override
	public void deleteById(long id, UUID enterpriseId, long footballCourtId) {
		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		ValidateId.validateLongId(id);
		validateSchedulingOwnership(footballCourtId, id);
		
		schedullingRepository.deleteById(id);
		
	}

	@Override
	public void deleteAllByFootballCourt(long footballCourtId, UUID enterpriseId) {
		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		ValidateId.validateLongId(footballCourtId);
		schedullingRepository.deleteAllByFootballCourt(footballCourtId);
	}

	@Override
	public void deleteAllByFootballCourtAndDate(long footballCourtId, LocalDate date, UUID enterpriseId) {
		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		
		ValidateId.validateLongId(footballCourtId);
		
		schedullingRepository.deleteAllByFootballCourtAndDate(footballCourtId, date);
		
	}
	
	@Override
	public List<Schedulling> findAllByIds(List<Long> schedulingsId){
		return schedullingRepository.findAllByIds(schedulingsId);
	}
	
	public void updateReservationsAndOrders(long orderId, List<Long> ids){
		schedullingRepository.updateReservationsAndOrders(orderId, ids);
	}
	
	
	public void validateSchedulingOwnership(Long footballCourtId, Long id) {
		
		Long schedulingDatabaseId = schedullingRepository.findFootballCourtByScheduleId(id);
		
		if(!schedulingDatabaseId.equals(footballCourtId)) {
			throw new SchedulingException(ExceptionSchedulingType.UNAUTHORIZED_SCHEDULING_ACCESS, null);
		}
		
	}
	
	public void validateNoOverlapWithDatabaseSchedules(List<Schedulling> databaseSchedullingsList, List<Schedulling> schedullingsList, List<String> overlappingSchedules) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Schedulling databaseSchedulling : databaseSchedullingsList) {
			for (Schedulling newSchedulling : schedullingsList) {
				if(newSchedulling.getStartTime().isBefore(databaseSchedulling.getEndTime()) && newSchedulling.getEndTime().isAfter(databaseSchedulling.getStartTime())) {
					
					stringBuilder.append(newSchedulling.getStartTime()).append(" - ").append(newSchedulling.getEndTime())
					.append(" está em conflito com ").append(databaseSchedulling.getStartTime()).append(" - ")
					.append(databaseSchedulling.getEndTime());
								
					overlappingSchedules.add(stringBuilder.toString());   
					stringBuilder.setLength(0);
				}
			}
		}
		
		if(!overlappingSchedules.isEmpty()){
			throw new SchedulingException(ExceptionSchedulingType.SCHEDULING_CONFLICT, overlappingSchedules);
		}
		
	}
	
	public void validateNoOverlapWithinRequest(List<Schedulling> schedulingsList, List<String> overlappingSchedules) {
		
		StringBuilder stringBuilder = new StringBuilder();
		List<StringBuilder> list = new ArrayList<>();
		for(int i = 1;i<schedulingsList.size();i++){
			if (schedulingsList.get(i - 1).getEndTime().isAfter(schedulingsList.get(i).getStartTime())) {
				stringBuilder.append(schedulingsList.get(i-1).getStartTime()).append(" - ").append(schedulingsList.get(i-1).getEndTime())
				.append(" está em conflito com ").append(schedulingsList.get(i).getStartTime()).append(" - ")
				.append(schedulingsList.get(i).getEndTime());
								
				overlappingSchedules.add(stringBuilder.toString());
			}
		}
		System.out.println(list);
		if(!overlappingSchedules.isEmpty()){
			throw new SchedulingException(ExceptionSchedulingType.SCHEDULING_CONFLICT, overlappingSchedules);
		}
	}

	
	public void teste (int n) {
		List<Integer> fibo = new ArrayList<>();
		fibo.add(0);
		fibo.add(1);
		
		for(int i = 2; i<n;i++) {
			var  value = fibo.get(i-2) + fibo.get(i-1);
			fibo.add(value);
			
		}
		System.out.println(fibo);
		
	}

}
