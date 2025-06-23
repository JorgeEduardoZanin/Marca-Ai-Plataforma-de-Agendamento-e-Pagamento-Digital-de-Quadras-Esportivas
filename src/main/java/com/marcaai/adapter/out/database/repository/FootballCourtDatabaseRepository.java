package com.marcaai.adapter.out.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcaai.adapter.out.database.entity.FootballCourtEntity;

@Repository
public interface FootballCourtDatabaseRepository extends JpaRepository<FootballCourtEntity, Long>{

}
