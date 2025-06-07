package com.marcaai.core.port.in;

import java.util.Map;
import java.util.UUID;

import com.marcaai.core.domain.User;

public interface UserCrudUseCase {

	void createUser(User user);
	
	User updateUser(UUID id,User user);
	
	Map<String, String> deleteUser(UUID id);
	
	User getUserById(UUID id);
	
	Map<String, String> updatePassword(UUID id, String password);
}
