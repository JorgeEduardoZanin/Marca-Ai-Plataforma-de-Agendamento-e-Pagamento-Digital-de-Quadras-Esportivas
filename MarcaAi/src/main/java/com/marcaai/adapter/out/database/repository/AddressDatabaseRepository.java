package com.marcaai.adapter.out.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.marcaai.adapter.out.database.entity.AddressEntity;

@Repository
public interface AddressDatabaseRepository extends JpaRepository<AddressEntity, Long>{
	
}
