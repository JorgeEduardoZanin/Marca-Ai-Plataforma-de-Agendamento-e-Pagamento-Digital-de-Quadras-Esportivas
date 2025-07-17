package com.marcaai.adapter.dto.response.usercrud;

import java.time.LocalDate;

public record UserCrudResponse(
		String name, 
		String phone_number, 
		String email,
		String cpf, 
		LocalDate dateOfBirth
		){}
