package com.marcaai.adapter.dto.request.usercrud;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserCrudRequest(
		@NotBlank String name, 
		@NotBlank String phone_number, 
		@NotBlank String cpf, 
		@NotBlank String email,
		@NotBlank String state, 
		@NotBlank String adress,
		@NotBlank String adress_number,
		@NotBlank String city,
		@NotBlank String CEP, 
		@NotBlank String neighborhood, 
		@NotBlank String complement,
		@NotNull LocalDate date_of_birth, 
		@NotBlank String password){}
