package com.marcaai.adapter.mapper;

import java.util.Optional;

import com.marcaai.adapter.dto.request.usercrud.CreateUserCrudRequest;
import com.marcaai.adapter.dto.request.usercrud.UpdateUserCrudRequest;
import com.marcaai.adapter.dto.response.usercrud.UserCrudResponse;
import com.marcaai.adapter.out.database.entity.AddressEntity;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.User;

public class UserMapper {

	public static User userRequestToUserDomain(CreateUserCrudRequest userRequest) {
		return new User(
				userRequest.name(),
				userRequest.phone_number(),
				userRequest.cpf(),
				userRequest.email(),
				userRequest.date_of_birth(),
				userRequest.password()
				);
	}
	
	public static User UserEntityToUserDomain(UserEntity userEntity) {
		User user = new User(
				userEntity.getName(),
				userEntity.getPhone_number(),
				userEntity.getCpf(),
				userEntity.getEmail(),
				userEntity.getDate_of_birth(),
				null
				);
		
		user.setId(userEntity.getId());
		var newAddress = new Address();
		newAddress.setId(userEntity.getAddressEntity().getId());
		
		user.setAddress(newAddress);
		return user;
		
	}
	
	public static UserEntity userDomainToUserEntity(User userDomain) {
		
		var newAdressEntity = new AddressEntity();
		newAdressEntity.setId(userDomain.getAddress().getId());
		
		UserEntity userEntity = new UserEntity(
					userDomain.getName(),
					userDomain.getCpf(), 
					userDomain.getCpf(),
					userDomain.getEmail(),
					userDomain.getDate_of_birth() != null ? userDomain.getDate_of_birth() : null,
					userDomain.getPassword() != null ? userDomain.getPassword() : null
					);
		
		userEntity.setAddressEntity(newAdressEntity);
		userEntity.setId(userDomain.getId() != null ? userDomain.getId() : null);
		userEntity.setRoles(userDomain.getRoles() != null ? RoleMapper.RoleDomainToSetRoleEntityUser(userDomain) : null);
		
		return userEntity;
	}
	
	public static User UpdateUserCrudRequestToUserDomain(UpdateUserCrudRequest updateUserCrudRequest) {
		return new User(
				updateUserCrudRequest.name(),
				updateUserCrudRequest.phone_number(),
				null,
				updateUserCrudRequest.email(),
				null,
				null
				);
	}
	
	public static UserCrudResponse UserCrudToUserCrudResponse(User userDomain) {
		return new UserCrudResponse(
				userDomain.getName(),
				userDomain.getPhone_number(),
				userDomain.getEmail(),
				userDomain.getCpf(),
				userDomain.getDate_of_birth()
				);
	}
	
	public static UserEntity UpdateUserDomainToUserEntity(User userDomain, UserEntity userEntity) {
		
		
		Optional.ofNullable(userDomain.getName()).ifPresent(userEntity::setName);
		Optional.ofNullable(userDomain.getPhone_number()).ifPresent(userEntity::setPhone_number);
		Optional.ofNullable(userDomain.getCpf()).ifPresent(userEntity::setCpf);
		Optional.ofNullable(userDomain.getEmail()).ifPresent(userEntity::setEmail);
		Optional.ofNullable(userDomain.getDate_of_birth()).ifPresent(userEntity::setDate_of_birth);
		Optional.ofNullable(userDomain.getPassword()).ifPresent(userEntity::setPassword);
		Optional.ofNullable(userDomain.getCreation_date()).ifPresent(userEntity::setCreation_date);
	
		userEntity.setAddressEntity(userEntity.getAddressEntity());
		userEntity.setRoles(userDomain.getRoles() != null ? RoleMapper.RoleDomainToSetRoleEntityUser(userDomain) : userEntity.getRoles());
		
		return userEntity;
	}
	
}
