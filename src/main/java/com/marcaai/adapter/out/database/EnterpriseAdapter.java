package com.marcaai.adapter.out.database;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.AddressMapper;
import com.marcaai.adapter.mapper.CompanyOwnerMapper;
import com.marcaai.adapter.mapper.EnterpriseMapper;
import com.marcaai.adapter.out.database.repository.EnterpriseDatabaseRepository;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.group.EnterpriseGrouping;
import com.marcaai.core.port.out.EnterpriseRepository;

@Component
public class EnterpriseAdapter implements EnterpriseRepository{

	private final EnterpriseDatabaseRepository enterpriseDatabaseRepository;
	
	public EnterpriseAdapter(EnterpriseDatabaseRepository enterpriseDatabaseRepository) {
		this.enterpriseDatabaseRepository = enterpriseDatabaseRepository;
	}

	@Override
	public void create(Enterprise enterprise) {
		enterpriseDatabaseRepository.save(EnterpriseMapper.enterpriseDomainToEnterpriseEntity(enterprise));
	}

	@Override
	public Enterprise update(Enterprise enterprise, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnterpriseGrouping findById(UUID id) {
		
		var findEnterprise = enterpriseDatabaseRepository.findById(id)
				.orElseThrow(() -> null);
		
		return new EnterpriseGrouping(EnterpriseMapper.enterpriseEntityToEnterpriseDomain(findEnterprise),
				AddressMapper.AddressEntityToAdressDomain(findEnterprise.getAddressEntity()),
				CompanyOwnerMapper.companyOwnerEntityToCompanyOwnerDomain(findEnterprise.getCompany_owner()));
		
	}

	@Override
	public void updatePassowrd(String password, UUID id) {
		// TODO Auto-generated method stub
		
	}

}
