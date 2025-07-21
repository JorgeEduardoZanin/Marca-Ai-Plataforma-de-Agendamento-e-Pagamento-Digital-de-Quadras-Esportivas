package com.marcaai.core.port.in;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface EmailUseCase {

	void emailVerification(String email, String code);
	
}
