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
		
		for (Schedulling databaseSchedulling : databaseSchedullingsList) {
			for (Schedulling newSchedulling : schedullingsList) {
				if(newSchedulling.getEndTime().isBefore(databaseSchedulling.getStartTime()) ||
						newSchedulling.getStartTime().isAfter(databaseSchedulling.getEndTime())) {
					System.out.println("erro");
				}
			}
			
		}
		
		for(Schedulling sched : schedullingsList) {
			for (DayOfWeek closedDays : footballCourt.getClosedDay()) {
				if(sched.getStartTime().getDayOfWeek() == closedDays) {
					System.out.println("erro");
				}
			}
		}

	for(int i = 1;i<schedullingsList.size();i++){
		if (schedullingsList.get(i - 1).getEndTime().isAfter(schedullingsList.get(i).getStartTime())) {
			System.out.println("erro");
		}
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
	

			
		if(schedullingsByFootballCourt.isEmpty()) {
			System.out.println("erro");
		}
			
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
			System.out.println("erro");
		}
		
	}

}
