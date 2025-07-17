package com.marcaai.core.port.out.internal;

import com.marcaai.core.domain.Role;

public interface RoleRepository {

	Role findByName(String roleName);
	
}
