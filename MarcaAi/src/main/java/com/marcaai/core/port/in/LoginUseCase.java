package com.marcaai.core.port.in;

import com.marcaai.core.domain.Login;

public interface LoginUseCase {

	Login userLogin(Login login);
	
	Login adminLogin(Login login);
	
	Login enterpriseLogin(Login login);
	
}
