package com.marcaai.core.usecase;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
import com.marcaai.core.port.in.SchedullingUseCase;
import com.marcaai.core.port.out.internal.SchedullingRepository;
import com.marcaai.core.usecase.utils.ValidateId;

public class SchedullingService implements SchedullingUseCase {

	private final SchedullingRepository schedullingRepository;

	private final FootballCourtService footballCourtService;

	public SchedullingService(SchedullingRepository schedullingRepository, FootballCourtService footballCourtService) {
		this.schedullingRepository = schedullingRepository;
		this.footballCourtService = footballCourtService;
	}

	@Override
	public Set<Schedulling> create(Set<Schedulling> schedullings, Long footballCourtId, UUID enterpriseId) {
		
		if(schedullings.isEmpty()) {
			throw new SchedulingException(ExceptionSchedulingType.REQUIRE_AT_LEAST_ONE_SCHEDULE , null);
		}
		
		List<String> overlappingSchedules = new ArrayList<>();
		
		ValidateId.validateLongId(footballCourtId);
		
		var footballCourt = footballCourtService.findById(footballCourtId, enterpriseId);
		
		schedullings = schedullings.stream()
		.map(s -> {
			s.setFootballCourt(footballCourt);
			s.setEndTime(s.getStartTime().plusMinutes(s.getDuration()));
			return s;
		})
		.filter(s -> s.getStartTime().isBefore(s.getEndTime()))
		.sorted(Comparator.comparing(Schedulling::getStartTime))
		.collect(Collectors.toCollection(LinkedHashSet::new));
		
		List<Schedulling> schedullingsList = new ArrayList<>(schedullings);
		
	
		var databaseSchedullingsList = schedullingRepository.findAllByFootballCourtAndDate(footballCourtId, schedullingsList.get(0).getStartTime().toLocalDate(),
				schedullingsList.get(schedullingsList.size()-1).getStartTime().toLocalDate());
		
		databaseSchedullingsList = databaseSchedullingsList.stream()
				.filter(date -> date.getEndTime().isAfter(date.getStartTime()))
				.toList();
		
		validateNoOverlapWithDatabaseSchedules(databaseSchedullingsList, schedullingsList, overlappingSchedules);
		
		
		for(Schedulling sched : schedullingsList) {
			for (DayOfWeek closedDays : footballCourt.getClosedDay()) {
				if(sched.getStartTime().getDayOfWeek() == closedDays) {
					throw new SchedulingException(ExceptionSchedulingType.CANNOT_SCHEDULE_ON_CLOSED_DAY, null);
				}
			}
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
	public Schedulling findById(Long id, Long footballCourtId, UUID enterpriseId) {
		validateSchedulingOwnership(footballCourtId, id);
		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		return schedullingRepository.findById(id);
	}

	@Override
	public List<Schedulling> findAllByFootballCourtAndDay(Long footballCourtId, LocalDate date, UUID enterpriseId) {

		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		
		var schedullingsByFootballCourt = schedullingRepository.findAllByFootballCourtAndDay(footballCourtId, date).stream()
				.sorted(Comparator.comparing(Schedulling::getStartTime))
				.toList();
			
		return schedullingsByFootballCourt;
				
	}

	@Override
	public Schedulling update(Schedulling schedulling, Long id) {
		
		
		ValidateId.validateLongId(id);
		
		schedulling.setId(id);
		
		return schedullingRepository.update(schedulling);
	}

	@Override
	public void deleteById(Long id, UUID enterpriseId, Long footballCourtId) {
		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		ValidateId.validateLongId(id);
		validateSchedulingOwnership(footballCourtId, id);
		
		schedullingRepository.deleteById(id);
		
	}

	@Override
	public void deleteAllByFootballCourt(Long footballCourtId, UUID enterpriseId) {
		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		ValidateId.validateLongId(footballCourtId);
		schedullingRepository.deleteAllByFootballCourt(footballCourtId);
	}

	@Override
	public void deleteAllByFootballCourtAndDate(Long footballCourtId, LocalDate date, UUID enterpriseId) {
		footballCourtService.validateEnterpriseOwnership(footballCourtId, enterpriseId);
		
		ValidateId.validateLongId(footballCourtId);
		
		schedullingRepository.deleteAllByFootballCourtAndDate(footballCourtId, date);
		
	}
	
	public void validateSchedulingOwnership(Long footballCourtId, Long id) {
		
		Long schedulingDatabaseId = schedullingRepository.findFootballCourtByScheduleId(id);
		
		if(!schedulingDatabaseId.equals(footballCourtId)) {
			throw new SchedulingException(ExceptionSchedulingType.UNAUTHORIZED_SCHEDULING_ACCESS, null);
		}
		
	}
	
	public void validateNoOverlapWithDatabaseSchedules(List<Schedulling> databaseSchedullingsList, List<Schedulling> schedullingsList, List<String> overlappingSchedules) {
		
		for (Schedulling databaseSchedulling : databaseSchedullingsList) {
			for (Schedulling newSchedulling : schedullingsList) {
				if(newSchedulling.getStartTime().isBefore(databaseSchedulling.getEndTime())
						 && newSchedulling.getEndTime().isAfter(databaseSchedulling.getStartTime())) {
					overlappingSchedules.add(newSchedulling.getStartTime()+" - "+newSchedulling.getEndTime()+" está em conflito com "
						 +databaseSchedulling.getStartTime()+" - "+databaseSchedulling.getEndTime());                         
				}
			}
		}
		
		if(!overlappingSchedules.isEmpty()){
			throw new SchedulingException(ExceptionSchedulingType.SCHEDULING_CONFLICT, overlappingSchedules);
		}
		
	}
	
	public void validateNoOverlapWithinRequest(List<Schedulling> schedulingsList, List<String> overlappingSchedules) {
		
		for(int i = 1;i<schedulingsList.size();i++){
			if (schedulingsList.get(i - 1).getEndTime().isAfter(schedulingsList.get(i).getStartTime())) {
				overlappingSchedules.add(schedulingsList.get(i-1).getStartTime()+" - "+ schedulingsList.get(i-1).getEndTime()+" está em conflito com "
						+schedulingsList.get(i).getStartTime()+" - "+ schedulingsList.get(i).getEndTime());                 
			}
		}
		
		if(!overlappingSchedules.isEmpty()){
			throw new SchedulingException(ExceptionSchedulingType.SCHEDULING_CONFLICT, overlappingSchedules);
		}
	}

}
