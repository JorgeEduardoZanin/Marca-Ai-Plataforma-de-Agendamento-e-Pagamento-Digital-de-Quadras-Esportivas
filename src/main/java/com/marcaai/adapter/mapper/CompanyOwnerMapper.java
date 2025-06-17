package com.marcaai.adapter.mapper;

import java.util.Optional;

import com.marcaai.adapter.dto.request.companyowner.CompanyOwnerRequest;
import com.marcaai.adapter.out.database.entity.CompanyOwnerEntity;
import com.marcaai.core.domain.CompanyOwner;

public class CompanyOwnerMapper {
	
	public static CompanyOwnerEntity companyOwnerDomainToCompanyOwnerEntity(CompanyOwner companyOwner) {
		return new CompanyOwnerEntity(companyOwner.getId(),
				companyOwner.getName(),
				companyOwner.getPhone_number(),
				companyOwner.getCpf(),
				companyOwner.getEmail(),
				companyOwner.getDate_of_birth()
				);
	}
	
	public static CompanyOwnerEntity updateCompanyOwnerDomainToCompanyOwnerEntity(CompanyOwner companyOwnerDomain, CompanyOwnerEntity companyOwnerEntity) {
		
		Optional.ofNullable(companyOwnerDomain.getName()).ifPresent(companyOwnerEntity::setName);
		Optional.ofNullable(companyOwnerDomain.getCpf()).ifPresent(companyOwnerEntity::setCpf);	
		Optional.ofNullable(companyOwnerDomain.getEmail()).ifPresent(companyOwnerEntity::setEmail);		
		Optional.ofNullable(companyOwnerDomain.getPhone_number()).ifPresent(companyOwnerEntity::setPhone_number);

		return companyOwnerEntity;
	}
	
	public static CompanyOwner companyOwnerEntityToCompanyOwnerDomain(CompanyOwnerEntity companyOwnerEntity) {
		CompanyOwner domain = new CompanyOwner();
		domain.setPhone_number(companyOwnerEntity.getPhone_number());
		domain.setDate_of_birth(companyOwnerEntity.getDate_of_birth());
		domain.setEmail(companyOwnerEntity.getEmail());
		domain.setName(companyOwnerEntity.getName());
		
		return domain;
	}
	
	public static CompanyOwner companyOwnerRequestToCompanyOwnerDomain(CompanyOwnerRequest comapanyOwner) {
		return new CompanyOwner(comapanyOwner.name(),
				comapanyOwner.phoneNumber(),
				comapanyOwner.cpf(),
				comapanyOwner.email(),
				comapanyOwner.dateOfBirth());
		
	}
}
