package com.marcaai.core.port.out.internal;

import java.util.UUID;

import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.group.EnterpriseDomainGrouping;

public interface EnterpriseRepository {

	void create(Enterprise enterprise);

	Enterprise update(Enterprise enterprise);
	
	void delete(UUID id);
	
	EnterpriseDomainGrouping findById(UUID id);
	
	void updatePassowrd(String password, UUID id);

	
}
