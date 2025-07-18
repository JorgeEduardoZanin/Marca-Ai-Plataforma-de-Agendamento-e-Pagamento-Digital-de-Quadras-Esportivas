package com.marcaai.core.port.out.internal;

import java.time.LocalDate;
import java.util.List;

import com.marcaai.core.domain.Schedulling;

public interface SchedullingRepository {

	List<Schedulling> create(List<Schedulling> schedullings);
		
	Schedulling findById(Long id);
	
	List<Schedulling> findAllByFootballCourtAndDate(Long footballCourtId, LocalDate initialDate, LocalDate finalDate);

	List<Schedulling> findAllByFootballCourtAndDay(Long footballCourtId, LocalDate date);
	
	Schedulling update(Schedulling schedulling);

	void deleteById(Long id);
	
	void deleteAllByFootballCourt(Long footballCourtId);
	
	void deleteAllByFootballCourtAndDate(Long footballCourtId, LocalDate date);
	
	Long findFootballCourtByScheduleId(Long id);
	
	void updateAvailability(List<Schedulling> schedulings);
	
	List<Schedulling> findAllByIds(List<Long> schedulingsId); 
	
	void updateReservationsAndOrders(Long orderId, List<Long> ids);
}
