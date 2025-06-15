package com.marcaai.adapter.mapper;

import com.marcaai.adapter.dto.request.enterprise.CreateEnterpriseRequest;
import com.marcaai.core.domain.Enterprise;

public class EnterpriseMapper {
	
	public static Enterprise createEnterpriseRequestToEnterpriseDomain(CreateEnterpriseRequest enterprise) {
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
