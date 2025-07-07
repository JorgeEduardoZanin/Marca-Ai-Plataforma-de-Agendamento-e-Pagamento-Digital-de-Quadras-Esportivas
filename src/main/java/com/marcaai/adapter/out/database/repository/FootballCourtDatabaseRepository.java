package com.marcaai.adapter.out.database.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.dto.response.FootballCourtRepositoryDatabaseResponse;
import com.marcaai.adapter.out.database.entity.FootballCourtEntity;

@Repository
public interface FootballCourtDatabaseRepository extends JpaRepository<FootballCourtEntity, Long>{

	@Query("SELECT f.enterpriseEntity.id FROM FootballCourtEntity f WHERE f.id = :footballCourtId")
	Optional<UUID> findEntepriseIdByFootballCourtId(Long footballCourtId); 
	
	@Query("SELECT new com.marcaai.adapter.out.database.dto.response.FootballCourtRepositoryDatabaseResponse(f.id, f.name, f.value, f.enterpriseEntity) FROM FootballCourtEntity f WHERE f.enterpriseEntity.id = :enterpriseId")
	Page<FootballCourtRepositoryDatabaseResponse> findAllPaginatedByEnterprise(UUID enterpriseId, Pageable pageable);
	
}
