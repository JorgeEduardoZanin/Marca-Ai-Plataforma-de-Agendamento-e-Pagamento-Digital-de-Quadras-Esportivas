package com.marcaai.adapter.out.database.adapter;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.CompanyOwnerMapper;
import com.marcaai.adapter.out.database.repository.CompanyOwnerDatabaseRepository;
import com.marcaai.adapter.out.database.repository.EnterpriseDatabaseRepository;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.port.out.internal.CompanyOwnerRepository;

@Component
public class CompanyOwnerAdapter implements CompanyOwnerRepository{

	private final CompanyOwnerDatabaseRepository companyOwnerDatabaseRepository;
	private final EnterpriseDatabaseRepository enterpriseDatabaseRepository;
	

	public CompanyOwnerAdapter(CompanyOwnerDatabaseRepository companyOwnerDatabaseRepository,
			EnterpriseDatabaseRepository enterpriseDatabaseRepository) {
		this.companyOwnerDatabaseRepository = companyOwnerDatabaseRepository;
		this.enterpriseDatabaseRepository = enterpriseDatabaseRepository;
	}

	@Override
	public UUID create(CompanyOwner companyOwner) {
	
		var findCompanyOwner = companyOwnerDatabaseRepository.findByCpf(companyOwner.getCpf());
		if(findCompanyOwner.isPresent()) {
			return findCompanyOwner.get().getId();
		}
		
		var newCompanyOwner = companyOwnerDatabaseRepository.save(
				CompanyOwnerMapper.companyOwnerDomainToCompanyOwnerEntity(companyOwner));
		
		return newCompanyOwner.getId();
	
		
		
	}

	@Override
	public CompanyOwner update(UUID id, CompanyOwner companyOwner) {
		
		var findEnterprise = enterpriseDatabaseRepository.findById(id);
				
		return CompanyOwnerMapper.companyOwnerEntityToCompanyOwnerDomain(companyOwnerDatabaseRepository.save(
				CompanyOwnerMapper.updateCompanyOwnerDomainToCompanyOwnerEntity(companyOwner, findEnterprise.get().getCompany_owner())));
				
	}

	@Override
	public CompanyOwner findById(UUID id) {
		return CompanyOwnerMapper.companyOwnerEntityToCompanyOwnerDomain(companyOwnerDatabaseRepository.findById(id)
				.orElseThrow(()-> null));
	}

}
