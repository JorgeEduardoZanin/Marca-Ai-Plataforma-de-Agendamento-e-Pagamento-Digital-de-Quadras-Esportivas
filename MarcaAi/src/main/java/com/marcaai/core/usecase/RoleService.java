package com.marcaai.core.usecase;

import com.marcaai.core.domain.Role;
import com.marcaai.core.port.out.internal.RoleRepository;

public class RoleService {

	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}


	public Role findRoleByName(String name) {
		return roleRepository.findByName(name);
	}
	
}
