package com.marcaai.core.port.in;


import java.util.UUID;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.group.EnterpriseDomainGrouping;
import com.marcaai.core.domain.group.EnterprisePaginationDomainGrouping;
import com.marcaai.core.domain.group.UpdateEnterpriseDomainGrouping;

public interface EnterpriseUseCase {

	void create(CompanyOwner companyOwner, Enterprise enterprise, Address address);
	
	UpdateEnterpriseDomainGrouping update(Enterprise enterprise, UUID id, Address address);
	
	EnterpriseDomainGrouping findById(UUID id);
	
	EnterprisePaginationDomainGrouping findAllPaginated(int size, int pageSize);
	
	void delete(UUID id);
	
	void updatePassword(UUID id, String password);
	
}
