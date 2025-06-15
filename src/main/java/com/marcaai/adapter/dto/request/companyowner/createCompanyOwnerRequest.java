package com.marcaai.adapter.dto.request.companyowner;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record createCompanyOwnerRequest(
		@NotBlank String name, 
		@NotBlank String phoneNumber,
		@NotBlank String cpf,
		@NotBlank String email,
		@NotBlank LocalDate dateOfBirth
		) {

}
