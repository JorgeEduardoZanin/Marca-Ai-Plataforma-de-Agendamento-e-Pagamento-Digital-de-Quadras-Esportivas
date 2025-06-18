package com.marcaai.adapter.out.database.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.entity.EnterpriseEntity;

@Repository
public interface EnterpriseDatabaseRepository extends JpaRepository<EnterpriseEntity, UUID>{

	@EntityGraph(attributePaths = {"addressEntity", "company_owner"})
	Optional<EnterpriseEntity> findById(UUID id);
	
	Optional<EnterpriseEntity> findByEmail(String email);
}
