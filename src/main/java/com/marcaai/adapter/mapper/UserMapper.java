package com.marcaai.adapter.mapper;


import com.marcaai.adapter.in.http.dto.request.UserCrudRequest;
import com.marcaai.adapter.in.http.dto.response.UserCrudResponse;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.core.domain.User;

public class UserMapper {

	public static User toUserDomain(UserCrudRequest userRequest) {
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
	
	public static UserCrudResponse toUserResponse(User user) {
		return new UserCrudResponse(
				user.getName(),
				user.getPhone_number(),
				user.getCpf(),
				user.getEmail(),
				user.getState(),
				user.getAdress(),
				user.getAdress_number(),
				user.getCity(),
				user.getCEP(),
				user.getNeighborhood(),
				user.getComplement(), 
				user.getDate_of_birth(), 
				user.getPassword()
				);
	}
	
	public static User UserEntitytoUserDomain(UserEntity userEntity) {
		return new User(
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
				userEntity.getPassword()
				);
	}
	
	public static UserEntity toUserEntity(User userDomain) {
		return new UserEntity(
				userDomain.getName(),
					userDomain.getPhone_number(), 
					userDomain.getCpf(),
					userDomain.getEmail(),
					userDomain.getState(),
					userDomain.getAdress(),
					userDomain.getAdress_number(),
					userDomain.getCity(),
					userDomain.getCEP(),
					userDomain.getNeighborhood(),
					userDomain.getComplement(),
					userDomain.getDate_of_birth(),
					userDomain.getPassword()
					);
	}
	
}
