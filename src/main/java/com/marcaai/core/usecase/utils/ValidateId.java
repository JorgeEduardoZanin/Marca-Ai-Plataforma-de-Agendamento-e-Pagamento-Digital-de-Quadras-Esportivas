package com.marcaai.core.usecase.utils;

import java.util.UUID;

public class ValidateId {

	public static void validateLongId(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");   
		}
	}
	
	public static void validateUUIDId(UUID id) {
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");   
		}
	}
	
}
