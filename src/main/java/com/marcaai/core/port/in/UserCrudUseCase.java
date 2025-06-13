package com.marcaai.core.port.in;

import java.util.UUID;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.User;
import com.marcaai.core.domain.group.UserAndAddressGrouping;

public interface UserCrudUseCase {

	void createUser(User user, Address address);
	
	UserAndAddressGrouping updateUser(UUID id, User user, Address address);
	
	void deleteUser(UUID id);
	
	UserAndAddressGrouping getUserById(UUID id);
	
	void updatePassword(UUID id, String password);
}
