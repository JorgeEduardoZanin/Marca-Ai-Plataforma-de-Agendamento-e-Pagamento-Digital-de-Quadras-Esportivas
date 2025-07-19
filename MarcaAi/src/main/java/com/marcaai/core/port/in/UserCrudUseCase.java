package com.marcaai.core.port.in;

import java.util.UUID;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.User;
import com.marcaai.core.domain.group.UserDomainGrouping;

public interface UserCrudUseCase {

	String createUser(User user, Address address);
	
	UserDomainGrouping updateUser(UUID id, User user, Address address);
	
	void deleteUser(UUID id);
	
	UserDomainGrouping getUserById(UUID id);
	
	void updatePassword(UUID id, String password);
}
