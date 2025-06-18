package com.marcaai.adapter.mapper;

import com.marcaai.adapter.dto.request.enterprise.EnterpriseRequest;
import com.marcaai.adapter.dto.response.enterprise.EnterpriseResponse;
import com.marcaai.adapter.out.database.entity.AddressEntity;
import com.marcaai.adapter.out.database.entity.CompanyOwnerEntity;
import com.marcaai.adapter.out.database.entity.EnterpriseEntity;
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
		newEnterprise.setMunicipal_registration(enterprise.municipalRegistration());
		
		return newEnterprise;
	}
	
	public static EnterpriseEntity enterpriseDomainToEnterpriseEntity(Enterprise enterprise) {
		
		var address = new AddressEntity();
		var companyOwner = new CompanyOwnerEntity();
	
		address.setId(enterprise.getAddress().getId());
		companyOwner.setId(enterprise.getCompany_owner().getId());
		
		var enterpriseEntity = new EnterpriseEntity(enterprise.getCorporate_reason(),
				enterprise.getFantasy_name(),
				enterprise.getCnpj(),
				enterprise.getEmail(),
				enterprise.getPassword(),
				enterprise.getPhone_number(),
				enterprise.getState_registration());
	
		enterpriseEntity.setMunicipal_registration(enterprise.getMunicipal_registration());
		enterpriseEntity.setAddressEntity(address);
		enterpriseEntity.setCompany_owner(companyOwner);
		enterpriseEntity.setRoles(RoleMapper.RoleDomainToSetRoleEntityEnterprise(enterprise));
		
		return enterpriseEntity;
	}
	
	public static Enterprise enterpriseEntityToEnterpriseDomain(EnterpriseEntity enterprise) {
		var newEnterprise = new Enterprise(enterprise.getCorporate_reason(),
				enterprise.getFantasy_name(),
				enterprise.getCnpj(),
				enterprise.getEmail(),
				null,
				enterprise.getPhone_number(),
				enterprise.getState_registration());
		
		newEnterprise.getMunicipal_registration();
		
		return newEnterprise;
		
	}
	
	
	public static EnterpriseResponse enterpriseDomainToEnterpriseResponse(Enterprise enterprise) {
		
		return new EnterpriseResponse(enterprise.getCorporate_reason(),
				enterprise.getFantasy_name(),
				enterprise.getCnpj(),
				enterprise.getEmail(),
				enterprise.getPhone_number(),
				enterprise.getState_registration(),
				enterprise.getMunicipal_registration());
		
	}
	

}
