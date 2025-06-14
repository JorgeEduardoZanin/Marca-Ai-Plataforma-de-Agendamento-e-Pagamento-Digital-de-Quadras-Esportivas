package com.marcaai.core.port.in;

import java.util.UUID;

import com.marcaai.core.domain.CompanyOwner;

public interface CompanyOwnerUseCase {

	CompanyOwner update(UUID id, CompanyOwner companyOwner);
	
}
