package com.marcaai.core.usecase;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.group.FootballCourtPaginationGrouping;
import com.marcaai.core.exception.FootballCourtException;
import com.marcaai.core.exception.enums.ExceptionFootballCourtType;
import com.marcaai.core.port.in.EnterpriseUseCase;
import com.marcaai.core.port.in.FootballCourtUseCase;
import com.marcaai.core.port.out.internal.FootballCourtRepository;
import com.marcaai.core.usecase.utils.ValidateId;

public class FootballCourtService implements FootballCourtUseCase{

	private final FootballCourtRepository footballCourtRepository;
	private final EnterpriseUseCase enterpriseUseCase;
	
	public FootballCourtService(FootballCourtRepository footballCourtRepository, EnterpriseUseCase enterpriseUseCase) {
		this.footballCourtRepository = footballCourtRepository;
		this.enterpriseUseCase = enterpriseUseCase;
	}

	@Override
	public FootballCourt create(FootballCourt footballCourt, UUID enterpriseId) {
		ValidateId.validateUUIDId(enterpriseId);
		
		var enterprise = enterpriseUseCase.findById(enterpriseId);
		footballCourt.setEnteprise(enterprise.enterprise());
		
		return footballCourtRepository.create(footballCourt);
	}
	
	@Override
	public FootballCourt findById(Long id, UUID enterpriseId) {
		ValidateId.validateLongId(id);
		
		return footballCourtRepository.findById(id);
	}

	@Override
	public FootballCourtPaginationGrouping findAllPaginatedByEnterprise(UUID enterpriseId, int page, int pageSize) {
		return footballCourtRepository.findAllPaginatedByEnterprise(enterpriseId ,page, pageSize);
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
	
	public List<FootballCourt> findAllByIds(Set<Long> footballCourtsids){
		return footballCourtRepository.findAllByIds(footballCourtsids);
	}
	
	public void validateEnterpriseOwnership (Long id, UUID enterpriseId) {
		UUID databaseEnterpriseId = footballCourtRepository.findEnterpriseUUIDInFootballCourt(id);
		
		if(!databaseEnterpriseId.equals(enterpriseId)) {
			throw new FootballCourtException(ExceptionFootballCourtType.UNAUTHORIZED_FOOTBALL_COURT_ACCESS);
		}
	}
	
	

}
