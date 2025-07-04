package com.marcaai.adapter.out.database.adapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marcaai.adapter.mapper.SchedullingMapper;
import com.marcaai.adapter.out.database.repository.SchedullingDatabaseRepository;
import com.marcaai.core.domain.Schedulling;
import com.marcaai.core.exception.SchedulingException;
import com.marcaai.core.exception.enums.ExceptionSchedulingType;
import com.marcaai.core.port.out.internal.SchedullingRepository;

@Component
@Transactional(rollbackFor = SchedulingException.class)
public class SchedullingAdapter implements SchedullingRepository {

	private final SchedullingDatabaseRepository schedullingDatabaseRepository;
	
	public SchedullingAdapter(SchedullingDatabaseRepository schedullingDatabaseRepository) {
		this.schedullingDatabaseRepository = schedullingDatabaseRepository;
	}


	@Override
	public List<Schedulling> create(List<Schedulling> schedullings) {
		
		var listSchedullingsEntitySave = schedullingDatabaseRepository.saveAll(SchedullingMapper.listSchedullingsDomainToListSchedullingsEntity(schedullings));
		
		return SchedullingMapper.listSchedullingEntityToListSchedullingDomain(listSchedullingsEntitySave);
	}


	@Override
	public Schedulling findById(Long id) {
		
		var schedulling = schedullingDatabaseRepository.findById(id)
				.orElseThrow(() -> new SchedulingException(ExceptionSchedulingType.SCHEDULING_NOT_FOUND, null));
		
		return SchedullingMapper.schedullingEntityToSchedullingDomain(schedulling);
	}


	@Override
	public List<Schedulling> findAllByFootballCourtAndDate(Long footballCourtId, LocalDate initialDate, LocalDate finalDate) {
		var schedullings = schedullingDatabaseRepository.findAllByFootballCourtAndDate(
				footballCourtId, initialDate.atTime(LocalTime.MIN), finalDate.atTime(LocalTime.MAX))
				.orElseThrow(() -> new SchedulingException(ExceptionSchedulingType.NO_SCHEDULINGS_FOR_COURT, null));

		return SchedullingMapper.listSchedullingEntityToListSchedullingDomain(schedullings);
	}
	
	@Override
	public List<Schedulling> findAllByFootballCourtAndDay(Long footballCourtId, LocalDate date) {
		var schedullings = schedullingDatabaseRepository.findAllByFootballCourtAndDate(
				footballCourtId, date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX))
					.orElseThrow(() -> new SchedulingException(ExceptionSchedulingType.NO_SCHEDULINGS_FOR_COURT, null));	

		return SchedullingMapper.listSchedullingEntityToListSchedullingDomain(schedullings);
	}


	@Override
	public Schedulling update(Schedulling schedulling) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		schedullingDatabaseRepository.deleteById(id);
		
	}

	@Override
	public void deleteAllByFootballCourt(Long footballCourtId) {
		schedullingDatabaseRepository.deleteAllByFootballCourtEntity_Id(footballCourtId);
		
	}


	@Override
	public void deleteAllByFootballCourtAndDate(Long footballCourtId, LocalDate date) {
		schedullingDatabaseRepository.deleteAllByFootballCourtAndDate(footballCourtId, date.atTime(LocalTime.MIN), date.atTime(LocalTime.MAX));
		
	}


	@Override
	public Long findFootballCourtByScheduleId(Long id) {
		return schedullingDatabaseRepository.findFootballCourtIdByScheduleId(id);
	}

}
