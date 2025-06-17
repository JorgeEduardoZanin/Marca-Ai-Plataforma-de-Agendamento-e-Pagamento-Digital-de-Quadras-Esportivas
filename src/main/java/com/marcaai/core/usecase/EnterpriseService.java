package com.marcaai.core.usecase;

import java.util.List;
import java.util.UUID;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.port.in.EnterpriseUseCase;

public class EnterpriseService implements EnterpriseUseCase {

	private final CompanyOwnerService companyOwnerService;
	private final AddressService addressService;
	
	
	public EnterpriseService(CompanyOwnerService companyOwnerService, AddressService addressService) {
		this.companyOwnerService = companyOwnerService;
		this.addressService = addressService;
	}

	@Override
	public void create(CompanyOwner companyOwner, Enterprise enterprise, Address address) {
		
		var createCompanyOwner = companyOwnerService.create(companyOwner);
		var createAddress = addressService.createAddress(address);
		enterprise.getAddress().setId(createAddress);
		enterprise.getCompany_owner().setId(createCompanyOwner);
		
	}

	@Override
	public Enterprise update(Enterprise enterprise, UUID id, Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enterprise findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enterprise> listPaginateEnterprise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
