package com.marcaai.core.port.out;

import java.util.UUID;

import com.marcaai.core.domain.User;

public interface UserCrudRepository {

	Long createUser(User user);
	
	User updateUser(User user);
	
	void deleteUser(UUID id);
	
	User getUserById(UUID id);
	
	void updatePassword(UUID id, String password);
	
	String findPasswordById(UUID id);
	
}
