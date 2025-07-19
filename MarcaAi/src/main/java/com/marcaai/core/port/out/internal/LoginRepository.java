package com.marcaai.core.port.out.internal;

import com.marcaai.core.domain.Login;
import com.marcaai.core.domain.group.LoginAndPermissionsGroup;

public interface LoginRepository {

	LoginAndPermissionsGroup findByUserEmail(String email);
	
	LoginAndPermissionsGroup findByEnterpriseEmail(String email);
	
	Login findByAdministratorEmail(String email);
	
}
