package com.marcaai.core.usecase;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.adapter.dto.grouping.response.EnterpriseResponseGrouping;
import com.marcaai.adapter.mapper.AddressMapper;
import com.marcaai.adapter.mapper.CompanyOwnerMapper;
import com.marcaai.adapter.mapper.EnterpriseMapper;
import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.group.CnpjGrouping;
import com.marcaai.core.domain.group.UpdateEnterpriseDomainGrouping;
import com.marcaai.core.port.in.EnterpriseUseCase;
import com.marcaai.core.port.out.external.CheckCnpjRepository;
import com.marcaai.core.port.out.internal.EnterpriseRepository;

public class EnterpriseService implements EnterpriseUseCase {

	private final CompanyOwnerService companyOwnerService;
	private final AddressService addressService;
	private final RoleService roleService;
	private final EnterpriseRepository enterpriseRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final CheckCnpjRepository checkCnpjRepository;

	public EnterpriseService(CompanyOwnerService companyOwnerService, AddressService addressService,
			RoleService roleService, EnterpriseRepository enterpriseRepository, BCryptPasswordEncoder passwordEncoder,
			CheckCnpjRepository checkCnpjRepository) {
		this.companyOwnerService = companyOwnerService;
		this.addressService = addressService;
		this.roleService = roleService;
		this.enterpriseRepository = enterpriseRepository;
		this.passwordEncoder = passwordEncoder;
		this.checkCnpjRepository = checkCnpjRepository;
	}

	@Override
	public void create(CompanyOwner companyOwner, Enterprise enterprise, Address address) {
		
		validateEnterprise(address, enterprise);
		
		var createCompanyOwner = companyOwnerService.create(companyOwner);
		var createAddress = addressService.createAddress(address);
		var role = roleService.findRoleByName(Role.Values.ENTERPRISE.name());
		
		Address newAddress = new Address();
		newAddress.setId(createAddress);
		
		CompanyOwner newCompanyOwner = new CompanyOwner();
		newCompanyOwner.setId(createCompanyOwner);
		
		enterprise.setPassword(passwordEncoder.encode(enterprise.getPassword()));
		enterprise.setRoles(Set.of(role));
		enterprise.setAddress(newAddress);
		enterprise.setCompany_owner(newCompanyOwner);
		
		enterpriseRepository.create(enterprise);
		
	}

	@Override
	public UpdateEnterpriseDomainGrouping update(Enterprise enterprise, UUID id, Address address) {
		
		validateId(id);
		enterprise.setId(id);
		var enterpriseUpdate = enterpriseRepository.update(enterprise);
		
		var addressUpdate = addressService.updateAddress(address, enterpriseUpdate.getAddress().getId());

		return new UpdateEnterpriseDomainGrouping(enterprise, addressUpdate);
	}

	@Override
	public EnterpriseResponseGrouping findById(UUID id) {
		
		validateId(id);
		var enterprise = enterpriseRepository.findById(id);
		
		return new EnterpriseResponseGrouping(AddressMapper.addressDomainToAddressResponse(enterprise.address()), 
				EnterpriseMapper.enterpriseDomainToEnterpriseResponse(enterprise.enterprise()),
				CompanyOwnerMapper.companyOwnerDomainToCompanyOwnerResponse(enterprise.companyOwner()));
	}

	@Override
	public List<Enterprise> listPaginateEnterprise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		validateId(id);
		enterpriseRepository.delete(id);
		
		
	}
	
	private void validateEnterprise(Address address, Enterprise enterprise) {
		CnpjGrouping addressAndCnpj = checkCnpjRepository.checkCnpj(enterprise.getCnpj());
		
		boolean corporateReasonOk = addressAndCnpj.cnpj().hasSameCorporateReason(enterprise);
		boolean cnpjActive = addressAndCnpj.cnpj().isActive();
		boolean addressEnterpriseOk =  addressAndCnpj.address().validateEnterpriseAddress(address);
		
		if(corporateReasonOk && addressEnterpriseOk && cnpjActive) {
			enterprise.setPartialApproved(true);
		}
	}
	
	@Override
	public void updatePassword(UUID id, String password) {
		validateId(id);
		
	}
	
	public void validateId(UUID id) {
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");      
		}
	}


}
