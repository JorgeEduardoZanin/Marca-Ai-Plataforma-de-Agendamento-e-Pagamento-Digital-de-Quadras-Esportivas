package com.marcaai.adapter.mapper;

import java.util.stream.Collectors;

import com.marcaai.adapter.dto.request.login.LoginRequest;
import com.marcaai.adapter.dto.response.login.LoginResponse;
import com.marcaai.adapter.out.database.entity.RoleEntity;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.core.domain.Login;
import com.marcaai.core.domain.Role;

public class LoginMapper {

	public static Login UserEntitytoLoginDomain(UserEntity userEntity) {
		
		Login login = new Login(userEntity.getEmail(), userEntity.getPassword());
		login.setRoles(userEntity.getRoles().stream()
			      .map(LoginMapper::toRoleDomain)
			      .collect(Collectors.toSet()));
		login.setId(userEntity.getId());
		
		return login;
	}
	
	public static Role toRoleDomain(RoleEntity roleEntity){
		return new Role(roleEntity.getRoleId(), roleEntity.getName());
	}
	
	public static Login LoginRequestToLoginDomain(LoginRequest loginRequest) {
		return new Login(loginRequest.email(), loginRequest.password());
	}
	
	public static LoginResponse loginDomainToLoginResponse (Login login) {
		return new LoginResponse(login.getToken(), login.getExpireIn());
	}
	
}
