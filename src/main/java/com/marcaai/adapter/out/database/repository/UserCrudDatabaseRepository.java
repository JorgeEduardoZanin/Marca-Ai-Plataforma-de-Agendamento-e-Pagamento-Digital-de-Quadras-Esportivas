package com.marcaai.adapter.out.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.entity.UserEntity;

@Repository
public interface UserCrudDatabaseRepository extends JpaRepository<UserEntity, UUID>{

}
