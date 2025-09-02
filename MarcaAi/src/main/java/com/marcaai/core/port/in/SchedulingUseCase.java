package com.marcaai.core.port.in;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.marcaai.core.domain.Schedulling;

public interface SchedulingUseCase {

	Set<Schedulling> create(Set<Schedulling> schedullings, long footballCourtId, UUID enterpriseId);
	
	Schedulling findById(long id, long footballCourtId, UUID enterpriseId);
	
	Set<Schedulling> findAllByFootballCourtAndDay(long footballCourtId, LocalDate date, UUID enterpriseId);
	
	Schedulling update(Schedulling schedulling, long id);
	
	void deleteById(long id, UUID enterpriseId, long footballCourtId);
	
	void deleteAllByFootballCourt(long footballCourtId, UUID enterpriseId);
	
	void deleteAllByFootballCourtAndDate(long footballCourtId, LocalDate date, UUID enterpriseId);
	
	List<Schedulling> findAllByIds(List<Long> schedulingsId);
	
	void updateReservationsAndOrders(long orderId, List<Long> ids);
}
