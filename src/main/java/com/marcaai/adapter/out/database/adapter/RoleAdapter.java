package com.marcaai.adapter.out.database.adapter;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.RoleMapper;
import com.marcaai.adapter.out.database.repository.RoleDatabaseRepository;
import com.marcaai.core.domain.Role;
import com.marcaai.core.exception.RoleException;
import com.marcaai.core.exception.enums.ExceptionRoleType;
import com.marcaai.core.port.out.internal.RoleRepository;

@Component
public class RoleAdapter implements RoleRepository{

	private final RoleDatabaseRepository roleDatabaseRepository;
	
	public RoleAdapter(RoleDatabaseRepository roleDatabaseRepository) {
		this.roleDatabaseRepository = roleDatabaseRepository;
	}


	@Override
	public Role findByName(String roleName) {
		
		var role = roleDatabaseRepository.findByName(roleName)
				.orElseThrow(()-> new RoleException(ExceptionRoleType.ROLE_NOT_FOUND));

		return RoleMapper.RoleEntityToRoleDomain(role);
	}

}
