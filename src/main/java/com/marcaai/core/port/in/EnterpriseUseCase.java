package com.marcaai.core.port.in;

import java.util.List;
import java.util.UUID;

import com.marcaai.adapter.dto.grouping.response.EnterpriseResponseGrouping;
import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.group.UpdateEnterpriseDomainGrouping;

public interface EnterpriseUseCase {

	void create(CompanyOwner companyOwner, Enterprise enterprise, Address address);
	
	UpdateEnterpriseDomainGrouping update(Enterprise enterprise, UUID id, Address address);
	
	EnterpriseResponseGrouping findById(UUID id);
	
	List<Enterprise> listPaginateEnterprise();
	
	void delete(UUID id);
	
	void updatePassword(UUID id, String password);
	
}
