package com.marcaai.adapter.out.database.adapter;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.AddressMapper;
import com.marcaai.adapter.mapper.CompanyOwnerMapper;
import com.marcaai.adapter.mapper.EnterpriseMapper;
import com.marcaai.adapter.out.database.repository.EnterpriseDatabaseRepository;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.group.EnterpriseDomainGrouping;
import com.marcaai.core.port.out.internal.EnterpriseRepository;

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
	public Enterprise update(Enterprise enterprise) {
		
		var enterpriseEntity = enterpriseDatabaseRepository.findById(enterprise.getId())
				.orElseThrow(() -> null);
		
		var enterpriseUpdate = enterpriseDatabaseRepository.saveAndFlush(EnterpriseMapper.updateEnterpriseDomainToEnterpriseEntity(enterpriseEntity, enterprise));
		
		return EnterpriseMapper.enterpriseEntityToEnterpriseDomain(enterpriseUpdate);
	}

	@Override
	public void delete(UUID id) {
		enterpriseDatabaseRepository.deleteById(id);
		
	}

	@Override
	public EnterpriseDomainGrouping findById(UUID id) {
		
		var findEnterprise = enterpriseDatabaseRepository.findById(id)
				.orElseThrow(() -> null);
		
		return new EnterpriseDomainGrouping(EnterpriseMapper.enterpriseEntityToEnterpriseDomain(findEnterprise),
				AddressMapper.addressEntityToAdressDomain(findEnterprise.getAddressEntity()),
				CompanyOwnerMapper.companyOwnerEntityToCompanyOwnerDomain(findEnterprise.getCompany_owner()));
		
	}

	@Override
	public void updatePassowrd(String password, UUID id) {
		// TODO Auto-generated method stub
		
	}

}
