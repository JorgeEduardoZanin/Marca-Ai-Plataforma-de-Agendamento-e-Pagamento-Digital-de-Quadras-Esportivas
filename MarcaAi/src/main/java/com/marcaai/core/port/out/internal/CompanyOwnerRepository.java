package com.marcaai.core.port.out.internal;

import java.util.UUID;

import com.marcaai.core.domain.CompanyOwner;

public interface CompanyOwnerRepository {

	UUID create(CompanyOwner companyOwner);
	
	CompanyOwner update(UUID id, CompanyOwner companyOwner);
	
	CompanyOwner findById(UUID id);
	
}
