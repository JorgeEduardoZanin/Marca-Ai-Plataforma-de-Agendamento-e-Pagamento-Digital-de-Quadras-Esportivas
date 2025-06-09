package com.marcaai.core.port.in;

import java.util.UUID;

import com.marcaai.core.domain.User;

public interface UserCrudUseCase {

	void createUser(User user);
	
	User updateUser(UUID id,User user);
	
	void deleteUser(UUID id);
	
	User getUserById(UUID id);
	
	void updatePassword(UUID id, String password);
}
