package com.marcaai.core.usecase;

import java.util.UUID;

import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.port.in.CompanyOwnerUseCase;
import com.marcaai.core.port.out.internal.CompanyOwnerRepository;

public class CompanyOwnerService implements CompanyOwnerUseCase{

	private final CompanyOwnerRepository companyOwnerRepository;

	public CompanyOwnerService(CompanyOwnerRepository companyOwnerRepository) {
		this.companyOwnerRepository = companyOwnerRepository;
	}
	
	@Override
	public UUID create(CompanyOwner companyOwner) {
		companyOwner.setCpf(companyOwner.getCpf().replaceAll("[^\\d]", ""));
		return companyOwnerRepository.create(companyOwner);
	}

	@Override
	public CompanyOwner update(UUID id, CompanyOwner companyOwner) {
		return companyOwnerRepository.update(id, companyOwner);
	}
	
}
