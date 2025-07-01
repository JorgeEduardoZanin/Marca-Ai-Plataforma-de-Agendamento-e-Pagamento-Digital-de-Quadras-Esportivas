package com.marcaai.core.port.in;

import java.util.List;
import java.util.UUID;

import com.marcaai.core.domain.FootballCourt;

public interface FootballCourtUseCase {

	FootballCourt create(FootballCourt footballCourt, UUID enterpriseId);
	
	FootballCourt findById(Long id, UUID enterpriseId);
	
	List<FootballCourt> listAll(UUID enterpriseId);
	
	void delete(Long id, UUID enterpriseId);
	
	FootballCourt update(FootballCourt footballCourt, Long id, UUID entepriseId);
	

}
