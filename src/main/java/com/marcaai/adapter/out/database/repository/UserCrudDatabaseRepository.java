package com.marcaai.adapter.out.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.entity.UserEntity;

@Repository
public interface UserCrudDatabaseRepository extends JpaRepository<UserEntity, UUID>{

	
	@Query("SELECT u.password FROM UserEntity u WHERE u.id = :id")
    String findPasswordById(UUID id);
	
	@Modifying
	@Query("UPDATE UserEntity u SET u.password = :password WHERE u.id = :id")
	void updatePassword(@Param("id") UUID id, @Param("password") String password);
	
}
