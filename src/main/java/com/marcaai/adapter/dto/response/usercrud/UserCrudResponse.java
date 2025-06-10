package com.marcaai.adapter.dto.response.usercrud;


public record UserCrudResponse(
		String name, 
		String phone_number, 
		String email,
		String state, 
		String adress,
		String adress_number,
		String city,
		String CEP, 
		String neighborhood, 
		String complement
		){}
