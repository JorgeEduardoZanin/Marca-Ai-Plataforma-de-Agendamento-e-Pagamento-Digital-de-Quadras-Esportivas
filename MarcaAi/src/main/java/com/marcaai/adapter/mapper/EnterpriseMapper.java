package com.marcaai.adapter.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.marcaai.adapter.dto.request.enterprise.EnterpriseRequest;
import com.marcaai.adapter.dto.request.enterprise.UpdateEnterpriseRequest;
import com.marcaai.adapter.dto.response.enterprise.EnterpriseResponse;
import com.marcaai.adapter.dto.response.enterprise.EnterpriseSummaryResponse;
import com.marcaai.adapter.out.database.dto.response.enterprise.EnterpriseDatabaseResponse;
import com.marcaai.adapter.out.database.entity.AddressEntity;
import com.marcaai.adapter.out.database.entity.CompanyOwnerEntity;
import com.marcaai.adapter.out.database.entity.EnterpriseEntity;
import com.marcaai.adapter.out.database.entity.UserPermissionsEntity;
import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.Enterprise;

public class EnterpriseMapper {
	
	public static Enterprise createEnterpriseRequestToEnterpriseDomain(EnterpriseRequest enterprise) {
		var newEnterprise = new Enterprise(enterprise.corporateReason(),
				enterprise.fantasyName(),
				enterprise.cnpj(),
				enterprise.email(),
				enterprise.password(),
				enterprise.phoneNumber(),
				enterprise.stateRegistration()
				);
		newEnterprise.setMunicipalRegistration(enterprise.municipalRegistration());
		
		return newEnterprise;
	}
	
	public static EnterpriseEntity updateEnterpriseDomainToEnterpriseEntity(EnterpriseEntity enterpriseEntity, Enterprise enterpriseDomain) {

		Optional.ofNullable(enterpriseDomain.getFantasyName()).ifPresent(enterpriseEntity::setFantasyName);
		Optional.ofNullable(enterpriseDomain.getEmail()).ifPresent(enterpriseEntity::setEmail);
		Optional.ofNullable(enterpriseDomain.getPhoneNumber()).ifPresent(enterpriseEntity::setPhoneNumber);
		Optional.ofNullable(enterpriseDomain.getStateRegistration()).ifPresent(enterpriseEntity::setStateRegistration);
		Optional.ofNullable(enterpriseDomain.getMunicipalRegistration()).ifPresent(enterpriseEntity::setMunicipalRegistration);
		Optional.ofNullable(enterpriseDomain.getCorporateReason()).ifPresent(enterpriseEntity::setCorporateReason);
		
		return enterpriseEntity;

	}
	
	public static EnterpriseEntity enterpriseDomainToEnterpriseEntity(Enterprise enterprise) {
		
		UserPermissionsEntity userPermissionsEntity = new UserPermissionsEntity(
				enterprise.getUserPermissions().getSendigCodeIn(),
				false,
				enterprise.getUserPermissions().getEmailVerificationCode());
		
		
		var address = new AddressEntity();
		var companyOwner = new CompanyOwnerEntity();
	
		address.setId(enterprise.getAddress().getId());
		companyOwner.setId(enterprise.getCompanyOwner().getId());
		
		var enterpriseEntity = new EnterpriseEntity(enterprise.getCorporateReason(),
				enterprise.getFantasyName(),
				enterprise.getCnpj(),
				enterprise.getEmail(),
				enterprise.getPassword(),
				enterprise.getPhoneNumber(),
				enterprise.getStateRegistration());
		
		enterpriseEntity.setPartialApproved(enterprise.isPartialApproved());
		enterpriseEntity.setMunicipalRegistration(enterprise.getMunicipalRegistration());
		enterpriseEntity.setAddressEntity(address);
		enterpriseEntity.setCompany_owner(companyOwner);
		enterpriseEntity.setRoles(RoleMapper.RoleDomainToSetRoleEntityEnterprise(enterprise));
		enterpriseEntity.setUserPermissionsEntity(userPermissionsEntity);
		
		return enterpriseEntity;
	}
	
	public static Enterprise enterpriseEntityToEnterpriseDomain(EnterpriseEntity enterprise) {
		var newEnterprise = new Enterprise(enterprise.getCorporateReason(),
				enterprise.getFantasyName(),
				enterprise.getCnpj(),
				enterprise.getEmail(),
				null,
				enterprise.getPhoneNumber(),
				enterprise.getStateRegistration());
		
		var address = new Address();
		address.setId(enterprise.getAddressEntity().getId());
		newEnterprise.setAddress(address);
		newEnterprise.setId(enterprise.getId());
		
		newEnterprise.getMunicipalRegistration();
		
		return newEnterprise;
		
	}
	
	
	public static EnterpriseResponse enterpriseDomainToEnterpriseResponse(Enterprise enterprise) {
		
		return new EnterpriseResponse(enterprise.getCorporateReason(),
				enterprise.getFantasyName(),
				enterprise.getCnpj(),
				enterprise.getEmail(),
				enterprise.getPhoneNumber(),
				enterprise.getStateRegistration(),
				enterprise.getMunicipalRegistration());
		
	}
	
	
	public static Enterprise updateEnterpriseRequestToEnterpriseDomain(UpdateEnterpriseRequest enterprise) {
		var newEnterprise = new Enterprise(enterprise.corporateReason(),
				enterprise.fantasyName(),
				null,
				enterprise.email(),
				null,
				enterprise.phoneNumber(),
				enterprise.stateRegistration());
		
		newEnterprise.setMunicipalRegistration(enterprise.municipalRegistration());
		
		return newEnterprise;
		
	}
	
	public static List<Enterprise> enterpriseRepositoryDatabaseResponseListToEnterpriseDomainList(Page<EnterpriseDatabaseResponse> enterpriseList){
		
		var enterpriseDomainList = enterpriseList.getContent().stream()
				.map(enterprise -> {
					var domain = new Enterprise();
					domain.setId(enterprise.id());
					domain.setFantasyName(enterprise.fantasyName());
					return domain;
				})
				.toList();
		
		return enterpriseDomainList;
		
	}
	
	public static List<EnterpriseSummaryResponse> enterpriseDomainListToEnterpriseSummaryResponseList(List<Enterprise> enterpriseList){
		
		var response = enterpriseList.stream()
				.map(domain -> {
					return new EnterpriseSummaryResponse(domain.getFantasyName(), domain.getId());
				})
				.toList();
		
		return response;
		
	}
	

}
