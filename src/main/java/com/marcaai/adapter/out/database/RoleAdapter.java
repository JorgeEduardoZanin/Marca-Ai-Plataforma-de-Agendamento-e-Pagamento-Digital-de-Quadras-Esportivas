package com.marcaai.adapter.out.database;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.RoleMapper;
import com.marcaai.adapter.out.database.repository.RoleDatabaseRepository;
import com.marcaai.core.domain.Role;
import com.marcaai.core.port.out.RoleRepository;

@Component
public class RoleAdapter implements RoleRepository{

	private final RoleDatabaseRepository roleDatabaseRepository;
	
	public RoleAdapter(RoleDatabaseRepository roleDatabaseRepository) {
		this.roleDatabaseRepository = roleDatabaseRepository;
	}


	@Override
	public Role findByName(String roleName) {
		
		var role = roleDatabaseRepository.findByName(roleName);
		if(role.isEmpty()) {
			return null;
		}
		
		return RoleMapper.RoleEntityToRoleDomain(role.get());
	}

}
