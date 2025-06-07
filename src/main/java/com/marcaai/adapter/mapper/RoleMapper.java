package com.marcaai.adapter.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.marcaai.adapter.out.database.entity.RoleEntity;
import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.User;

public class RoleMapper {

	public static Role RoleEntityToRoleDomain(RoleEntity roleEntity) {
		return new Role(roleEntity.getRoleId(), roleEntity.getName());
	}
	
	public static Set<RoleEntity> RoleDomainToSetRoleEntity(User user) {
		
		return user.getRoles().stream()
				.map(RoleMapper::roleDomainToRoleEntity)
				.collect(Collectors.toSet());
		
	}
	
	public static RoleEntity roleDomainToRoleEntity(Role role) {
		return new RoleEntity(role.getRoleId(), role.getName());
	}
	
}
