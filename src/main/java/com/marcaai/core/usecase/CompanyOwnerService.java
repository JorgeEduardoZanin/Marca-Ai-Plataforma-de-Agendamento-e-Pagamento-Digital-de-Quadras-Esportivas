package com.marcaai.core.usecase;

import java.util.UUID;

import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.port.in.CompanyOwnerUseCase;
import com.marcaai.core.port.out.CompanyOwnerRepository;

public class CompanyOwnerService implements CompanyOwnerUseCase{

	private final CompanyOwnerRepository companyOwnerRepository;

	public CompanyOwnerService(CompanyOwnerRepository companyOwnerRepository) {
		this.companyOwnerRepository = companyOwnerRepository;
	}
	
	public UUID create(CompanyOwner companyOwner) {
		return companyOwnerRepository.create(companyOwner);
	}
	
	public CompanyOwner findById(UUID entepriseId) {
		return companyOwnerRepository.findById(entepriseId);
	}

	@Override
	public CompanyOwner update(UUID id, CompanyOwner companyOwner) {
		return companyOwnerRepository.update(id, companyOwner);
	}
	
}
