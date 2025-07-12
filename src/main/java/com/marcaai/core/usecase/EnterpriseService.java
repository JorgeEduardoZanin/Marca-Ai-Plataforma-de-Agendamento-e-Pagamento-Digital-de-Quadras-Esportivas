package com.marcaai.core.usecase;

import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.group.CnpjGrouping;
import com.marcaai.core.domain.group.EnterpriseDomainGrouping;
import com.marcaai.core.domain.group.EnterprisePaginationDomainGrouping;
import com.marcaai.core.domain.group.UpdateEnterpriseDomainGrouping;
import com.marcaai.core.exception.EnterpriseException;
import com.marcaai.core.exception.enums.ExceptionEnterpriseType;
import com.marcaai.core.port.in.CompanyOwnerUseCase;
import com.marcaai.core.port.in.EnterpriseUseCase;
import com.marcaai.core.port.out.external.CheckCnpjRepository;
import com.marcaai.core.port.out.internal.EnterpriseRepository;
import com.marcaai.core.usecase.utils.ValidateId;

public class EnterpriseService implements EnterpriseUseCase {

	private final CompanyOwnerUseCase companyOwnerUseCase;
	private final AddressService addressService;
	private final RoleService roleService;
	private final EnterpriseRepository enterpriseRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final CheckCnpjRepository checkCnpjRepository;

	public EnterpriseService(CompanyOwnerUseCase companyOwnerUseCase, AddressService addressService,
			RoleService roleService, EnterpriseRepository enterpriseRepository, BCryptPasswordEncoder passwordEncoder,
			CheckCnpjRepository checkCnpjRepository) {
		this.companyOwnerUseCase = companyOwnerUseCase;
		this.addressService = addressService;
		this.roleService = roleService;
		this.enterpriseRepository = enterpriseRepository;
		this.passwordEncoder = passwordEncoder;
		this.checkCnpjRepository = checkCnpjRepository;
	}

	@Override
	public void create(CompanyOwner companyOwner, Enterprise enterprise, Address address) {
		
		validateEnterprise(address, enterprise);
		
		var createCompanyOwner = companyOwnerUseCase.create(companyOwner);
		var createAddress = addressService.createAddress(address);
		var role = roleService.findRoleByName(Role.Values.ENTERPRISE.name());
		
		Address newAddress = new Address();
		newAddress.setId(createAddress);
		
		CompanyOwner newCompanyOwner = new CompanyOwner();
		newCompanyOwner.setId(createCompanyOwner);
		
		enterprise.setPassword(passwordEncoder.encode(enterprise.getPassword()));
		enterprise.setRoles(Set.of(role));
		enterprise.setAddress(newAddress);
		enterprise.setCompanyOwner(newCompanyOwner);
		
		enterpriseRepository.create(enterprise);
		
	}

	@Override
	public UpdateEnterpriseDomainGrouping update(Enterprise enterprise, UUID id, Address address) {
		
		ValidateId.validateUUIDId(id);
		enterprise.setId(id);
		var enterpriseUpdate = enterpriseRepository.update(enterprise);
		
		var addressUpdate = addressService.updateAddress(address, enterpriseUpdate.getAddress().getId());

		return new UpdateEnterpriseDomainGrouping(enterprise, addressUpdate);
	}

	@Override
	public EnterpriseDomainGrouping findById(UUID id) {
		
		ValidateId.validateUUIDId(id);
		var enterpriseGroup = enterpriseRepository.findById(id);
		
		return enterpriseGroup;
	}

	@Override
	public EnterprisePaginationDomainGrouping findAllPaginated(int size, int pageSize) {
		return enterpriseRepository.findAllPaginated(size, pageSize);
	}

	@Override
	public void delete(UUID id) {
		ValidateId.validateUUIDId(id);
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
		
		ValidateId.validateUUIDId(id);
		String oldPassword = enterpriseRepository.findPasswordById(id);
		
		if(passwordEncoder.matches(password, oldPassword)) {
			throw new EnterpriseException(ExceptionEnterpriseType.NEW_PASSWORD_SAME_AS_PREVIOUS_ONE);
		}
		
		enterpriseRepository.updatePassowrd(passwordEncoder.encode(password), id);
	}

}
