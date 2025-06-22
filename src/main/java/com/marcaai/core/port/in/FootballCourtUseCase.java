package com.marcaai.core.port.in;

import java.util.Set;
import java.util.UUID;

import com.marcaai.core.domain.FootballCourt;

public interface FootballCourtUseCase {

	FootballCourt create(FootballCourt footballCourt, UUID enterpriseId);
	
	Set<FootballCourt> listAll(UUID enterpriseId);
	
	void delete(Long id);
	
	FootballCourt update();
	
}
