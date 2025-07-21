package com.marcaai.core.port.out.internal;

public interface EmailRepository {

	Object findEmailVerification(String email);
	
}
