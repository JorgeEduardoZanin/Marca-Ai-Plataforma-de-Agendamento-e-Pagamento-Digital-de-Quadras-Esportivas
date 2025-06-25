package com.marcaai.core.port.in;

import java.util.List;
import java.util.Set;

import com.marcaai.core.domain.Schedulling;

public interface SchedullingUseCase {

	Set<Schedulling> create(Set<Schedulling> schedullings);
	
	Schedulling findById(Long id);
	
	List<Schedulling> listAllByFootballCourt(Long footballCourtId);
	
	Schedulling update(Schedulling schedulling, Long id);
	
	void delete(Long id);
	
	
}
