package com.marcaai.adapter.mapper;

import com.marcaai.adapter.dto.request.usercrud.CreateUserCrudRequest;
import com.marcaai.adapter.dto.request.usercrud.UpdateUserCrudRequest;
import com.marcaai.adapter.dto.response.usercrud.UserCrudResponse;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.core.domain.User;

public class UserMapper {

	public static User toUserDomain(CreateUserCrudRequest userRequest) {
		return new User(
				userRequest.name(),
				userRequest.phone_number(),
				userRequest.cpf(),
				userRequest.email(),
				userRequest.state(),
				userRequest.adress(), 
				userRequest.adress_number(),
				userRequest.city(),
				userRequest.CEP(),
				userRequest.neighborhood(),
				userRequest.complement(), 
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
				userEntity.getState(),
				userEntity.getAdress(), 
				userEntity.getAdress_number(),
				userEntity.getCity(),
				userEntity.getCEP(),
				userEntity.getNeighborhood(),
				userEntity.getComplement(), 
				userEntity.getDate_of_birth(),
				null
				);
		
		return user;
		
	}
	
	public static UserEntity toUserEntity(User userDomain) {
		UserEntity userEntity = new UserEntity(
					userDomain.getName(),
					userDomain.getCpf(), 
					userDomain.getCpf(),
					userDomain.getEmail(),
					userDomain.getState(),
					userDomain.getAdress(),
					userDomain.getAdress_number(),
					userDomain.getCity(),
					userDomain.getCEP(),
					userDomain.getNeighborhood(),
					userDomain.getComplement(),
					userDomain.getDate_of_birth() != null ? userDomain.getDate_of_birth() : null,
					userDomain.getPassword() != null ? userDomain.getPassword() : null
					);
		
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
				updateUserCrudRequest.state(),
				updateUserCrudRequest.adress(), 
				updateUserCrudRequest.adress_number(),
				updateUserCrudRequest.city(),
				updateUserCrudRequest.CEP(),
				updateUserCrudRequest.neighborhood(),
				updateUserCrudRequest.complement(),
				null,
				null
				);
	}
	
	public static UserCrudResponse UserToUpdateUserCrudResponse(User userDomain) {
		return new UserCrudResponse(
				userDomain.getName(),
				userDomain.getPhone_number(),
				userDomain.getEmail(),
				userDomain.getState(),
				userDomain.getAdress(), 
				userDomain.getAdress_number(),
				userDomain.getCity(),
				userDomain.getCEP(),
				userDomain.getNeighborhood(),
				userDomain.getComplement()
				);
	}
	
	public static UserEntity UpdateUserEntityToUserEntity(User userDomain, UserEntity userFindById) {
		UserEntity userEntity = new UserEntity(
					userDomain.getName() != null ? userDomain.getName() : userFindById.getName(),
					userDomain.getCpf() != null ? userDomain.getPhone_number() : userFindById.getPhone_number(), 
					userDomain.getCpf() != null ? userDomain.getCpf() : userFindById.getCpf(),
					userDomain.getEmail() != null ? userDomain.getEmail() : userFindById.getEmail(),
					userDomain.getState() != null  ? userDomain.getState() : userFindById.getState(),
					userDomain.getAdress() != null ? userDomain.getAdress() : userFindById.getAdress(),
					userDomain.getAdress_number() != null ? userDomain.getAdress_number() : userFindById.getAdress_number(),
					userDomain.getCity() != null ? userDomain.getCity() : userFindById.getCity(),
					userDomain.getCEP() != null ? userDomain.getCEP() : userFindById.getCEP(),
					userDomain.getNeighborhood() != null ? userDomain.getNeighborhood() : userFindById.getNeighborhood(),
					userDomain.getComplement() != null ? userDomain.getComplement() : userFindById.getComplement(),
					userDomain.getDate_of_birth() != null ? userDomain.getDate_of_birth() : userFindById.getDate_of_birth(),
					userDomain.getPassword() != null ? userDomain.getPassword() : userFindById.getPassword()
					);
		
		userEntity.setCreation_date(userDomain.getCreation_date() != null ? userDomain.getCreation_date() : userFindById.getCreation_date());
		userEntity.setId(userDomain.getId() != null ? userDomain.getId() : userFindById.getId());
		userEntity.setRoles(userDomain.getRoles() != null ? RoleMapper.RoleDomainToSetRoleEntity(userDomain) : userFindById.getRoles());
		
		return userEntity;
	}
	
}
