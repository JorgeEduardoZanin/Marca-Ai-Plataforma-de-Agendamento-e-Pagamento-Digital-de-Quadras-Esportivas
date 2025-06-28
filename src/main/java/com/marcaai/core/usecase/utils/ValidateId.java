package com.marcaai.core.usecase.utils;

public class ValidateId {

	public static void validateLongId(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");   
		}
	}
	
}
