package com.marcaai.core.port.out;

import com.marcaai.core.domain.Login;

public interface LoginRepository {

	Login findByEmail(String email);
	
}
