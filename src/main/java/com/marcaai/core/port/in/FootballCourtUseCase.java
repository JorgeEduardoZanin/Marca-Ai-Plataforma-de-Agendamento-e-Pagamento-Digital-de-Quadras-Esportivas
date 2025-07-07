package com.marcaai.core.port.in;

import java.util.UUID;

import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.group.FootballCourtPaginationGrouping;

public interface FootballCourtUseCase {

	FootballCourt create(FootballCourt footballCourt, UUID enterpriseId);
	
	FootballCourt findById(Long id, UUID enterpriseId);
	
	FootballCourtPaginationGrouping findAllPaginatedByEnterprise(UUID enterpriseId, int page, int pageSize);
	
	void delete(Long id, UUID enterpriseId);
	
	FootballCourt update(FootballCourt footballCourt, Long id, UUID entepriseId);
	

}
