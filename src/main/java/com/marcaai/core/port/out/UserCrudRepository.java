package com.marcaai.core.port.out;

import java.util.Map;
import java.util.UUID;

import com.marcaai.core.domain.User;

public interface UserCrudRepository {

	void createUser(User user);
	
	User updateUser(User user);
	
	Map<String, String> deleteUser(UUID id);
	
	User getUserById(UUID id);
	
	Map<String, String> updatePassword(UUID id, String password);
	
}
