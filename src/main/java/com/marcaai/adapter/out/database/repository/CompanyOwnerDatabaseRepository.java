package com.marcaai.adapter.out.database.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.marcaai.adapter.out.database.entity.CompanyOwnerEntity;


@Repository
public interface CompanyOwnerDatabaseRepository extends JpaRepository<CompanyOwnerEntity, UUID>{

	Optional<CompanyOwnerEntity> findByCpf(String cpf);
	
	@Query("SELECT c.id FROM CompanyOwnerEntity c WHERE c.id = :id")
    String findIdById(UUID id);
	
}
