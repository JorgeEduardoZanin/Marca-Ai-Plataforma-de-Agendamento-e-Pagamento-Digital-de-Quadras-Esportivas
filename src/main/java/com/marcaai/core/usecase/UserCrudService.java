package com.marcaai.core.usecase;

import java.util.Map;

import com.marcaai.core.domain.User;
import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.UserCrudRepository;

public class UserCrudService implements UserCrudUseCase{

	private final UserCrudRepository userCrudRepository;
	
	public UserCrudService(UserCrudRepository userCrudRepository) {
		this.userCrudRepository = userCrudRepository;
	}

	@Override
	public User createUser(User user) {
		User saveUser = userCrudRepository.createUser(user);
		return saveUser;
	}

	@Override
	public User updateUser(Long id, User user) {
		
		return null;
	}

	@Override
	public Map<String, String> deleteUser(Long id) {
		
		return null;
	}

	@Override
	public User getUserById(Long id) {
		
		return null;
	}

}
