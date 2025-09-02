package com.marcaai.core.port.in;


public interface EmailUseCase {

	void emailVerification(String email, String code);
	
}
