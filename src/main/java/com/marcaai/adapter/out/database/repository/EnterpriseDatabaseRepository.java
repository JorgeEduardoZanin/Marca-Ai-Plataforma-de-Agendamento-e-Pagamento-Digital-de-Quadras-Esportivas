package com.marcaai.adapter.out.database.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.dto.response.enterprise.EnterpriseDatabaseResponse;
import com.marcaai.adapter.out.database.entity.EnterpriseEntity;

@Repository
public interface EnterpriseDatabaseRepository extends JpaRepository<EnterpriseEntity, UUID>{

	@EntityGraph(attributePaths = {"addressEntity", "company_owner"})
	Optional<EnterpriseEntity> findById(UUID id);
	
	Optional<EnterpriseEntity> findByEmail(String email);
	
	@Query("SELECT new com.marcaai.adapter.out.database.dto.response.EnterpriseRepositoryDatabaseResponse(e.id, e.fantasyName) FROM EnterpriseEntity e WHERE e.fullyApproved = true")
	Page<EnterpriseDatabaseResponse> findAllPaginated(Pageable pageable);
	
	@Query("SELECT e.password FROM EnterpriseEntity e WHERE e.id = :id")
	String findPasswordById(UUID id);
	
	@Modifying
	@Query("UPDATE EnterpriseEntity e SET e.password = :password WHERE e.id = :id")
	void updatePassword(String password, UUID id);
	
}
