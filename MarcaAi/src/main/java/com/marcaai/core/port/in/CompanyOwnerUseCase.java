package com.marcaai.core.port.in;

import java.util.UUID;

import com.marcaai.core.domain.CompanyOwner;

public interface CompanyOwnerUseCase {
	
	UUID create(CompanyOwner companyOwner); 

	CompanyOwner update(UUID enterpriseId, CompanyOwner companyOwner);

}
