package com.marcaai.core.port.out;

import java.util.Map;

import com.marcaai.core.domain.User;

public interface UserCrudRepository {

	User createUser(User user);
	
	User updateUser(Long id, User user);
	
	Map<String, String> deleteUser(Long id);
	
	User getUserById(Long id);
	
}
