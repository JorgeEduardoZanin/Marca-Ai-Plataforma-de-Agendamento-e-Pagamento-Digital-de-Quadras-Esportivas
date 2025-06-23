package com.marcaai.core.usecase;

import java.util.List;
import java.util.UUID;

import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.port.in.FootballCourtUseCase;
import com.marcaai.core.port.out.internal.FootballCourtRepository;

public class FootballCourtService implements FootballCourtUseCase{

	private FootballCourtRepository footballCourtRepository;
	
	public FootballCourtService(FootballCourtRepository footballCourtRepository) {
		this.footballCourtRepository = footballCourtRepository;
	}

	@Override
	public FootballCourt create(FootballCourt footballCourt, UUID enterpriseId) {
		
		var enterprise = new Enterprise();
		enterprise.setId(enterpriseId);
		footballCourt.setEnteprise(enterprise);
		
		return footballCourtRepository.create(footballCourt);
	}
	
	@Override
	public FootballCourt findById(Long id) {
		validateId(id);
		return footballCourtRepository.findById(id);
	}

	@Override
	public List<FootballCourt> listAll(UUID enterpriseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		validateId(id);
		footballCourtRepository.delete(id);
		
	}

	@Override
	public FootballCourt update(FootballCourt footballCourt, Long id) {
		validateId(id);
		footballCourtRepository.update(footballCourt, id);
		return null;
	}
	
	public void validateId(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");      
		}
	}

}
