package com.marcaai.core.port.in;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.marcaai.core.domain.Schedulling;

public interface SchedulingUseCase {

	Set<Schedulling> create(Set<Schedulling> schedullings, Long footballCourtId, UUID enterpriseId);
	
	Schedulling findById(Long id, Long footballCourtId, UUID enterpriseId);
	
	Set<Schedulling> findAllByFootballCourtAndDay(Long footballCourtId, LocalDate date, UUID enterpriseId);
	
	Schedulling update(Schedulling schedulling, Long id);
	
	void deleteById(Long id, UUID enterpriseId, Long footballCourtId);
	
	void deleteAllByFootballCourt(Long footballCourtId, UUID enterpriseId);
	
	void deleteAllByFootballCourtAndDate(Long footballCourtId, LocalDate date, UUID enterpriseId);
	
	List<Schedulling> findAllByIds(List<Long> schedulingsId);
	
	void updateReservationsAndOrders(Long orderId, List<Long> ids);
}
