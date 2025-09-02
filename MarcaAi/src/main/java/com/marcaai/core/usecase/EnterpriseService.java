package com.marcaai.core.usecase;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.UserPermissions;
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
import com.marcaai.core.usecase.utils.RandomNumber;
import com.marcaai.core.usecase.utils.ValidateId;

public class EnterpriseService implements EnterpriseUseCase {

	private final CompanyOwnerUseCase companyOwnerUseCase;
	private final AddressService addressService;
	private final RoleService roleService;
	private final EnterpriseRepository enterpriseRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final CheckCnpjRepository checkCnpjRepository;
	private final EmailService emailService;

	public EnterpriseService(CompanyOwnerUseCase companyOwnerUseCase, AddressService addressService,
			RoleService roleService, EnterpriseRepository enterpriseRepository, BCryptPasswordEncoder passwordEncoder,
			CheckCnpjRepository checkCnpjRepository, EmailService emailService) {
		this.companyOwnerUseCase = companyOwnerUseCase;
		this.addressService = addressService;
		this.roleService = roleService;
		this.enterpriseRepository = enterpriseRepository;
		this.passwordEncoder = passwordEncoder;
		this.checkCnpjRepository = checkCnpjRepository;
		this.emailService = emailService;
	}


	@Override
	@CacheEvict(cacheNames = "enterprises", allEntries = true)
	public void create(CompanyOwner companyOwner, Enterprise enterprise, Address address) {
		
		enterprise.setCnpj(enterprise.getCnpj().replaceAll("[^\\d]", ""));
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
		
		
		var randomNumberVerification = Integer.toString(RandomNumber.sixDigitRandomNumber());
		emailService.sendEmailVerification(enterprise.getEmail(), randomNumberVerification);
		UserPermissions userPermissions = new UserPermissions(LocalDateTime.now().plusSeconds(5), false, randomNumberVerification);
		enterprise.setUserPermissions(userPermissions);
		
		enterpriseRepository.create(enterprise);
		
	}

	@Override
    @CacheEvict(cacheNames = "enterprise", key = "#id")
	public UpdateEnterpriseDomainGrouping update(Enterprise enterprise, UUID id, Address address) {
		
		ValidateId.validateUUIDId(id);
		enterprise.setId(id);
		var enterpriseUpdate = enterpriseRepository.update(enterprise);
		
		var addressUpdate = addressService.updateAddress(address, enterpriseUpdate.getAddress().getId());

		return new UpdateEnterpriseDomainGrouping(enterprise, addressUpdate);
	}
	
	@Override
    @CacheEvict(cacheNames = "enterprise", key = "#id")
	public void updatePassword(UUID id, String password) {
		
		ValidateId.validateUUIDId(id);
		String oldPassword = enterpriseRepository.findPasswordById(id);
		
		if(passwordEncoder.matches(password, oldPassword)) {
			throw new EnterpriseException(ExceptionEnterpriseType.NEW_PASSWORD_SAME_AS_PREVIOUS_ONE);
		}
		
		enterpriseRepository.updatePassowrd(passwordEncoder.encode(password), id);
	}

	@Override
	@Cacheable(cacheNames = "enterprise")
	public EnterpriseDomainGrouping findById(UUID id) {
		
		ValidateId.validateUUIDId(id);
		var enterpriseGroup = enterpriseRepository.findById(id);
		System.out.println("🔍 [MISS] Indo ao banco de dados!");
		return enterpriseGroup;
	}

	@Override
	@Cacheable(cacheNames = "enterprises", sync = true)
	public EnterprisePaginationDomainGrouping findAllPaginated(int page , int pageSize) {
		System.out.println("🔍 [MISS] Indo ao banco de dados!");
		return enterpriseRepository.findAllPaginated(page, pageSize);
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
	


}
