package com.marcaai.adapter.out.database.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.entity.SchedullingEntity;

import feign.Param;

@Repository
public interface SchedullingDatabaseRepository extends JpaRepository<SchedullingEntity, Long>{
	
	void deleteAllByFootballCourtEntity_Id(Long id);
	
	@Query("DELETE FROM SchedullingEntity s WHERE s.footballCourtEntity.id = :footballCourtId AND s.startTime BETWEEN :start AND :end ")
	void deleteAllByFootballCourtAndDate(
			@Param("footballCourtId") Long footballCourtId, 
			@Param("start") LocalDateTime start, 
			@Param("end") LocalDateTime end);
	
	@Query("SELECT s FROM SchedullingEntity s WHERE s.footballCourtEntity.id = :footballCourtId AND s.startTime BETWEEN :start AND :end ")
	Optional<List<SchedullingEntity>> findAllByFootballCourtAndDate(
			@Param("footballCourtId") Long footballCourtId, 
			@Param("start") LocalDateTime start, 
			@Param("end") LocalDateTime end);
	
	@Query("SELECT s.footballCourtEntity.id FROM SchedullingEntity s WHERE s.id = :id")
	Long findFootballCourtIdByScheduleId(Long id);
	
	@Query("SELECT s FROM SchedullingEntity s WHERE s.id IN (:ids) AND s.reserved = false")
	List<SchedullingEntity> findAllByIdInAndReservedFalse(List<Long> ids);
	
	@Modifying
	@Query("UPDATE SchedullingEntity s SET s.reserved = true, s.orderEntity.id = :orderId WHERE s.id IN :ids")
	void updateReservationsAndOrders(Long orderId, List<Long> ids);
	
}
