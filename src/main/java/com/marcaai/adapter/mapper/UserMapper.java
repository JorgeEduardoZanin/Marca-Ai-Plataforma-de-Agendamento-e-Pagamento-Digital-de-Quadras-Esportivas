package com.marcaai.adapter.mapper;

import com.marcaai.adapter.dto.request.usercrud.CreateUserCrudRequest;
import com.marcaai.adapter.dto.request.usercrud.UpdateUserCrudRequest;
import com.marcaai.adapter.dto.response.usercrud.UserCrudResponse;
import com.marcaai.adapter.out.database.entity.AddressEntity;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.User;

public class UserMapper {

	public static User toUserDomain(CreateUserCrudRequest userRequest) {
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
		
		var newAddress = new Address();
		newAddress.setId(userEntity.getAddressEntity().getId());
		
		user.setAddress(newAddress);
		return user;
		
	}
	
	public static UserEntity toUserEntity(User userDomain) {
		
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
		userEntity.setRoles(userDomain.getRoles() != null ? RoleMapper.RoleDomainToSetRoleEntity(userDomain) : null);
		
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
	
	public static UserCrudResponse UserToUpdateUserCrudResponse(User userDomain) {
		return new UserCrudResponse(
				userDomain.getName(),
				userDomain.getPhone_number(),
				userDomain.getEmail()
				);
	}
	
	public static UserEntity UpdateUserDomainToUserEntity(User userDomain, UserEntity userFindById) {
		UserEntity userEntity = new UserEntity(
					userDomain.getName() != null ? userDomain.getName() : userFindById.getName(),
					userDomain.getCpf() != null ? userDomain.getPhone_number() : userFindById.getPhone_number(), 
					userDomain.getCpf() != null ? userDomain.getCpf() : userFindById.getCpf(),
					userDomain.getEmail() != null ? userDomain.getEmail() : userFindById.getEmail(),
					userDomain.getDate_of_birth() != null ? userDomain.getDate_of_birth() : userFindById.getDate_of_birth(),
					userDomain.getPassword() != null ? userDomain.getPassword() : userFindById.getPassword()
					);
		
		userEntity.setAddressEntity(userFindById.getAddressEntity());
		userEntity.setCreation_date(userDomain.getCreation_date() != null ? userDomain.getCreation_date() : userFindById.getCreation_date());
		userEntity.setId(userDomain.getId() != null ? userDomain.getId() : userFindById.getId());
		userEntity.setRoles(userDomain.getRoles() != null ? RoleMapper.RoleDomainToSetRoleEntity(userDomain) : userFindById.getRoles());
		
		return userEntity;
	}
	
}
