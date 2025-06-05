package com.marcaai.adapter.out.database;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.UserMapper;
import com.marcaai.adapter.out.database.repository.UserCrudDatabaseRepository;
import com.marcaai.core.domain.User;
import com.marcaai.core.port.out.UserCrudRepository;

@Component
public class UserCrudAdapter implements UserCrudRepository {

	private final UserCrudDatabaseRepository userCrudDatabaseRepository;

	public UserCrudAdapter(UserCrudDatabaseRepository userCrudDatabaseRepository) {
		this.userCrudDatabaseRepository = userCrudDatabaseRepository;
	}

	@Override
	public User createUser(User user) {
		User userSave = UserMapper.UserEntitytoUserDomain(userCrudDatabaseRepository.save(UserMapper.toUserEntity(user)));
		return userSave;
	}

	@Override
	public User updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> deleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
