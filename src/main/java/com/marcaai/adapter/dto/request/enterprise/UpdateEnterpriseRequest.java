package com.marcaai.adapter.dto.request.enterprise;


public record UpdateEnterpriseRequest(
		String corporateReason,
		String fantasyName,
		String email,
		String phoneNumber,
		String stateRegistration,
		String municipalRegistration) {

}
