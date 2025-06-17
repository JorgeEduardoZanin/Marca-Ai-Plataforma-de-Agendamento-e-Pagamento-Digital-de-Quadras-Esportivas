package com.marcaai.core.port.in;

import java.util.List;
import java.util.UUID;
import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.domain.Enterprise;

public interface EnterpriseUseCase {

	void create(CompanyOwner companyOwner, Enterprise enterprise, Address address);
	
	Enterprise update(Enterprise enterprise, UUID id, Address address);
	
	Enterprise findById(UUID id);
	
	List<Enterprise> listPaginateEnterprise();
	
	void delete(UUID id);
	
}
