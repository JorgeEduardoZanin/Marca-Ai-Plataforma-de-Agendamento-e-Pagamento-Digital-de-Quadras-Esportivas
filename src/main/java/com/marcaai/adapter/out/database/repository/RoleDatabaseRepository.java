package com.marcaai.adapter.out.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.entity.RoleEntity;


@Repository
public interface RoleDatabaseRepository extends JpaRepository<RoleEntity, Long>  {

	Optional<RoleEntity> findByName(String name);
	
}
