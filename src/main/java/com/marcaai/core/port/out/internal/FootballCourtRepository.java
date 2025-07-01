package com.marcaai.core.port.out.internal;

import java.util.List;
import java.util.UUID;

import com.marcaai.core.domain.FootballCourt;

public interface FootballCourtRepository {

	FootballCourt create(FootballCourt footballCourt);
	
	FootballCourt findById(Long id);
	
	List<FootballCourt> listAll();
	
	FootballCourt update(FootballCourt footballCourt, Long id);
	
	void delete(Long id);
	
	UUID findEnterpriseUUIDInFootballCourt(Long id);
	
	
}
