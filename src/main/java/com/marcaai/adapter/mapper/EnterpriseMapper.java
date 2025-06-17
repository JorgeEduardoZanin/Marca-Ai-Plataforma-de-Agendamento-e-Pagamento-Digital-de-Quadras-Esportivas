package com.marcaai.adapter.mapper;

import com.marcaai.adapter.dto.request.enterprise.EnterpriseRequest;
import com.marcaai.core.domain.Enterprise;

public class EnterpriseMapper {
	
	public static Enterprise createEnterpriseRequestToEnterpriseDomain(EnterpriseRequest enterprise) {
		return new Enterprise(enterprise.corporateReason(),
				enterprise.fantasyName(),
				enterprise.cnpj(),
				enterprise.email(),
				enterprise.password(),
				enterprise.phoneNumber(),
				enterprise.stateRegistration()
				);
		
	}
	

}
