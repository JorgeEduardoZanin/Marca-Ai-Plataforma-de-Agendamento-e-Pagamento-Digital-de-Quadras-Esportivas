package com.marcaai.adapter.out.database.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.entity.FootballCourtEntity;

@Repository
public interface FootballCourtDatabaseRepository extends JpaRepository<FootballCourtEntity, Long>{

	@Query("SELECT f.enterpriseEntity.id FROM FootballCourtEntity f WHERE f.id = :footballCourtId")
	Optional<UUID> findEntepriseIdByFootballCourtId(Long footballCourtId); 
	
}
