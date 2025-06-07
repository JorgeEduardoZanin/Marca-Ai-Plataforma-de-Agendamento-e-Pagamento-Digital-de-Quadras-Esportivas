package com.marcaai.adapter.dto.request;

import java.time.LocalDate;

public record CreateUserCrudRequest(
		String name, 
		String phone_number, 
		String cpf, 
		String email,
		String state, 
		String adress,
		String adress_number,
		String city,
		String CEP, 
		String neighborhood, 
		String complement,
		LocalDate date_of_birth, 
		String password){}
