package com.marcaai.adapter.out.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcaai.adapter.out.database.entity.OrderEntity;

public interface OrderDatabaseRepository extends JpaRepository<OrderEntity, Long>{

}
