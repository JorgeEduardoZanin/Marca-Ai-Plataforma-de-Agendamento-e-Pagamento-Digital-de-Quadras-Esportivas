package com.marcaai.core.usecase;

import java.util.List;
import java.util.UUID;

import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.port.in.FootballCourtUseCase;
import com.marcaai.core.port.out.internal.FootballCourtRepository;
import com.marcaai.core.usecase.utils.ValidateId;

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
	public FootballCourt findById(Long id, UUID enterpriseId) {
		ValidateId.validateLongId(id);
		validateEnterpriseOwnership(id, enterpriseId);
		
		return footballCourtRepository.findById(id);
	}

	@Override
	public List<FootballCourt> listAll(UUID enterpriseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id, UUID enterpriseId) {
		ValidateId.validateLongId(id);
		validateEnterpriseOwnership(id, enterpriseId);
		
		footballCourtRepository.delete(id);
		
	}

	@Override
	public FootballCourt update(FootballCourt footballCourt, Long id, UUID enterpriseId) {
		ValidateId.validateLongId(id);
		validateEnterpriseOwnership(id, enterpriseId);
		
		footballCourtRepository.update(footballCourt, id);
		return null;
	}
	
	public void validateEnterpriseOwnership (Long id, UUID enterpriseId) {
		UUID databaseEnterpriseId = footballCourtRepository.findEnterpriseUUIDInFootballCourt(id);
		
		if(!databaseEnterpriseId.equals(enterpriseId)) {
			System.out.println("erro");
		}
	}
	
	

}
