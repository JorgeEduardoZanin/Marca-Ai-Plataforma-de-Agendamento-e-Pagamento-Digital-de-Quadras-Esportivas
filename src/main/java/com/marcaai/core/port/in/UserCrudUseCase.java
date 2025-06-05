package com.marcaai.core.port.in;

import java.util.Map;
import com.marcaai.core.domain.User;

public interface UserCrudUseCase {

	User createUser(User user);
	
	User updateUser(Long id, User user);
	
	Map<String, String> deleteUser(Long id);
	
	User getUserById(Long id);
	
}
