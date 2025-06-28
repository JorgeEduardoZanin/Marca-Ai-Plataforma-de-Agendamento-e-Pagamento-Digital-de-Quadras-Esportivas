package com.marcaai.core.port.out.internal;

import java.util.List;

import com.marcaai.core.domain.Schedulling;

public interface SchedullingRepository {

	List<Schedulling> create(List<Schedulling> schedullings);
		
	Schedulling findById(Long id);

	List<Schedulling> listAllByFootballCourt(Long footballCourtId);
	
	Schedulling update(Schedulling schedulling);

	void deleteById(Long id);
	
	void deleteAllByFootballCourt(Long footballCourtId);
}
