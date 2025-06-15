package com.marcaai.adapter.dto.request.enterprise;

import jakarta.validation.constraints.NotBlank;

public record CreateEnterpriseRequest(
		@NotBlank String corporateReason,
		@NotBlank String fantasyName,
		@NotBlank String cnpj,
		@NotBlank String email,
		@NotBlank String password,
		@NotBlank String phoneNumber,
		@NotBlank String stateRegistration,
		String municipalRegistration
		) {

}
