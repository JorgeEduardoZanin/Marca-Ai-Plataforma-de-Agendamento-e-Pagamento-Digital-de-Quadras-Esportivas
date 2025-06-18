package com.marcaai.core.port.out;

import com.marcaai.core.domain.Login;

public interface LoginRepository {

	Login findByUserEmail(String email);
	
	Login findByEnterpriseEmail(String email);
	
	Login findByAdministratorEmail(String email);
	
}
