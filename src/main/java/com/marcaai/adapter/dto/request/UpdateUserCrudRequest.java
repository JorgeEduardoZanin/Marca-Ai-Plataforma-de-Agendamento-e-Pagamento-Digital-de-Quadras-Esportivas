package com.marcaai.adapter.dto.request;

public record UpdateUserCrudRequest(String name, 
		String phone_number, 
		String cpf, 
		String email,
		String state, 
		String adress,
		String adress_number,
		String city,
		String CEP, 
		String neighborhood, 
		String complement) {
	

}
