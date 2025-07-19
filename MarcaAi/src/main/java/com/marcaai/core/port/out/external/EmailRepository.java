package com.marcaai.core.port.out.external;

public interface EmailRepository {

	void sendEmailVerification(String to, String code);
	
}
