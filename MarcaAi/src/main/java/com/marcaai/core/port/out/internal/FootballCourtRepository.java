package com.marcaai.core.port.out.internal;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.group.FootballCourtPaginationGrouping;

public interface FootballCourtRepository {

	FootballCourt create(FootballCourt footballCourt);
	
	FootballCourt findById(Long id);
	
	FootballCourtPaginationGrouping findAllPaginatedByEnterprise(UUID enterpriseId ,int page, int pageSize);
	
	FootballCourt update(FootballCourt footballCourt, Long id);
	
	void delete(Long id);
	
	UUID findEnterpriseUUIDInFootballCourt(Long id);
	
	List<FootballCourt> findAllByIds(Set<Long> footballCourtsIds);
	
}
