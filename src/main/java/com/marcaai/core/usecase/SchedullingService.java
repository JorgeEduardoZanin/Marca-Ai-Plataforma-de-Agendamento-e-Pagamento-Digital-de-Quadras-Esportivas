package com.marcaai.core.usecase;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
	public Set<Schedulling> create(Set<Schedulling> schedullings, Long footballCourtId) {
		
		ValidateId.validateLongId(footballCourtId);
		
		var footballCourt = footballCourtService.findById(footballCourtId);
		
		
		
		schedullings = schedullings.stream()
		.peek(s -> s.setFootballCourt(footballCourt))
		.peek(s -> s.setEndTime(s.getStartTime().plusMinutes(s.getDuration())))
		.filter(s -> s.getStartTime().isBefore(s.getEndTime()))
		.sorted(Comparator.comparing(Schedulling::getStartTime))
		.collect(Collectors.toCollection(LinkedHashSet::new));
		
		
		
		List<Schedulling> schedullingsList = new ArrayList<>(schedullings);
		
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

	return schedullingsList.stream().sorted(Comparator.comparing(Schedulling::getStartTime)).collect(Collectors.toCollection(LinkedHashSet::new));

	}

	@Override
	public Schedulling findById(Long id) {
		return schedullingRepository.findById(id);
	}

	@Override
	public List<Schedulling> listAllByFootballCourt(Long footballCourtId) {
		return schedullingRepository.listAllByFootballCourt(footballCourtId);
	}

	@Override
	public Schedulling update(Schedulling schedulling, Long id) {
		
		ValidateId.validateLongId(id);
		
		schedulling.setId(id);
		
		return schedullingRepository.update(schedulling);
	}

	@Override
	public void deleteById(Long id) {
		ValidateId.validateLongId(id);
		schedullingRepository.deleteById(id);
		
	}

	@Override
	public void deleteAllByFootballCourt(Long footballCourtId) {
		ValidateId.validateLongId(footballCourtId);
		schedullingRepository.deleteAllByFootballCourt(footballCourtId);
	}

}
