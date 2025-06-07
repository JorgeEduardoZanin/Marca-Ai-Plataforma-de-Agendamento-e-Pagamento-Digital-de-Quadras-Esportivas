package com.marcaai.core.port.out;

import com.marcaai.core.domain.Role;

public interface RoleRepository {

	Role findByName(String roleName);
	
}
