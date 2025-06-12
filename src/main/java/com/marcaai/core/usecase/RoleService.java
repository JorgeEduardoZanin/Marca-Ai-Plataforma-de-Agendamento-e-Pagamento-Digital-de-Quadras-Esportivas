package com.marcaai.core.usecase;

import com.marcaai.core.domain.Role;
import com.marcaai.core.port.out.RoleRepository;

public class RoleService {

	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}


	public Role findBasicRole() {
		return roleRepository.findByName(Role.Values.BASIC.name());
	}
}
