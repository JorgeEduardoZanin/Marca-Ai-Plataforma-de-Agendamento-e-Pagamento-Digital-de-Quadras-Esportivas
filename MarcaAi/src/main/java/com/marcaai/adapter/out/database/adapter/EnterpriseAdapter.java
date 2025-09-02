package com.marcaai.adapter.out.database.adapter;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.AddressMapper;
import com.marcaai.adapter.mapper.CompanyOwnerMapper;
import com.marcaai.adapter.mapper.EnterpriseMapper;
import com.marcaai.adapter.out.database.repository.EnterpriseDatabaseRepository;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.group.EnterpriseDomainGrouping;
import com.marcaai.core.domain.group.EnterprisePaginationDomainGrouping;
import com.marcaai.core.exception.EnterpriseException;
import com.marcaai.core.exception.enums.ExceptionEnterpriseType;
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
				.orElseThrow(() -> new EnterpriseException(ExceptionEnterpriseType.ENTERPRISE_NOT_FOUND));
		
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
				.orElseThrow(() -> new EnterpriseException(ExceptionEnterpriseType.ENTERPRISE_NOT_FOUND));
		
		return new EnterpriseDomainGrouping(EnterpriseMapper.enterpriseEntityToEnterpriseDomain(findEnterprise),
				AddressMapper.addressEntityToAdressDomain(findEnterprise.getAddressEntity()),
				CompanyOwnerMapper.companyOwnerEntityToCompanyOwnerDomain(findEnterprise.getCompany_owner()));
		
	}

	@Override
	public void updatePassowrd(String password, UUID id) {
		enterpriseDatabaseRepository.updatePassword(password, id);	
	}

	@Override
	public EnterprisePaginationDomainGrouping findAllPaginated(int size, int pageSize) {
		var enterpriseList = enterpriseDatabaseRepository.findAllPaginated(PageRequest.of(size, pageSize, Sort.Direction.ASC, "fantasyName"));
		System.out.println("🔍 [MISS] Indo ao banco de dados!");
		return new EnterprisePaginationDomainGrouping(EnterpriseMapper.enterpriseRepositoryDatabaseResponseListToEnterpriseDomainList(enterpriseList),
				enterpriseList.getTotalElements(),
				enterpriseList.getTotalPages());
		
	}

	@Override
	public String findPasswordById(UUID id) {
		return enterpriseDatabaseRepository.findPasswordById(id);
	}

}
