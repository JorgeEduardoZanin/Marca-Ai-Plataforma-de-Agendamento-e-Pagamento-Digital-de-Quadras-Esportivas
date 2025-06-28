package com.marcaai.core.port.in;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.marcaai.core.domain.Schedulling;

public interface SchedullingUseCase {

	Set<Schedulling> create(Set<Schedulling> schedullings, Long footballCourtId);
	
	Schedulling findById(Long id);
	
	List<Schedulling> findAllByFootballCourtAndDate(Long footballCourtId, LocalDate date);
	
	Schedulling update(Schedulling schedulling, Long id);
	
	void deleteById(Long id);
	
	void deleteAllByFootballCourt(Long footballCourtId);
	
	void deleteAllByFootballCourtAndDate(Long footballCourtId, LocalDate date);
}
