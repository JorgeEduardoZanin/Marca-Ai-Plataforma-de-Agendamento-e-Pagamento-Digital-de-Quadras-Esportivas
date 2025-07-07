package com.marcaai.core.port.out.internal;

import java.util.UUID;

import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.group.FootballCourtPaginationGroup;

public interface FootballCourtRepository {

	FootballCourt create(FootballCourt footballCourt);
	
	FootballCourt findById(Long id);
	
	FootballCourtPaginationGroup findAllPaginatedByEnterprise(UUID enterpriseId ,int page, int pageSize);
	
	FootballCourt update(FootballCourt footballCourt, Long id);
	
	void delete(Long id);
	
	UUID findEnterpriseUUIDInFootballCourt(Long id);
	
	
}
