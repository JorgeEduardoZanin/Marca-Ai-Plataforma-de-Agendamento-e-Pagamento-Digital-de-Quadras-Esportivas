package com.marcaai.adapter.dto.request.companyowner;

import java.time.LocalDate;

public record UpdateCompanyOwnerRequest(
		String name, 
		String phoneNumber,
		String email,
		LocalDate dateOfBirth
		) {}
