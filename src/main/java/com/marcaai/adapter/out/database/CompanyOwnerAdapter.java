package com.marcaai.adapter.out.database;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.CompanyOwnerMapper;
import com.marcaai.adapter.out.database.repository.CompanyOwnerDatabaseRepository;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.port.out.CompanyOwnerRepository;

@Component
public class CompanyOwnerAdapter implements CompanyOwnerRepository{

	private final CompanyOwnerDatabaseRepository companyOwnerDatabaseRepository;
	
	public CompanyOwnerAdapter(CompanyOwnerDatabaseRepository companyOwnerDatabaseRepository) {
		this.companyOwnerDatabaseRepository = companyOwnerDatabaseRepository;
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
		
		var findCompanyOwner = companyOwnerDatabaseRepository.findById(id);
				
		var save = companyOwnerDatabaseRepository.save(
				CompanyOwnerMapper.updateCompanyOwnerDomainToCompanyOwnerEntity(companyOwner, findCompanyOwner.get()));
		
		return CompanyOwnerMapper.companyOwnerEntityToCompanyOwnerDomain(save);
				
	}

	@Override
	public CompanyOwner findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
