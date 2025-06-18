package com.marcaai.core.port.out;

import java.util.UUID;

import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.group.EnterpriseGrouping;

public interface EnterpriseRepository {

	void create(Enterprise enterprise);

	Enterprise update(Enterprise enterprise, UUID id);
	
	void delete(UUID id);
	
	EnterpriseGrouping findById(UUID id);
	
	void updatePassowrd(String password, UUID id);

	
}
