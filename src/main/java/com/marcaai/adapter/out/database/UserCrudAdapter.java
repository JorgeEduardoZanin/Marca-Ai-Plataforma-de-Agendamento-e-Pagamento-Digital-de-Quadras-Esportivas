package com.marcaai.adapter.out.database;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.UserMapper;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.adapter.out.database.repository.UserCrudDatabaseRepository;
import com.marcaai.core.domain.User;
import com.marcaai.core.exception.UserCrudException;
import com.marcaai.core.exception.enums.ExceptionUserCrudType;
import com.marcaai.core.port.out.UserCrudRepository;

import jakarta.transaction.Transactional;

@Component
public class UserCrudAdapter implements UserCrudRepository {

	private final UserCrudDatabaseRepository userCrudDatabaseRepository;

	public UserCrudAdapter(UserCrudDatabaseRepository userCrudDatabaseRepository) {
		this.userCrudDatabaseRepository = userCrudDatabaseRepository;
	}

	@Override
	public void createUser(User user) {
		UserMapper.UserEntityToUserDomain(userCrudDatabaseRepository.save(UserMapper.toUserEntity(user)));
	}

	@Override
	public User updateUser(User user) {
		
		UserEntity findUserById = userCrudDatabaseRepository.findById(user.getId())
				.orElseThrow(() -> new UserCrudException(ExceptionUserCrudType.USER_NOT_FOUND));
		
		user.setId(findUserById.getId());
		
		var userDomain = userCrudDatabaseRepository.saveAndFlush(UserMapper.UpdateUserEntityToUserEntity(user, findUserById));
		
		return UserMapper.UserEntityToUserDomain(userDomain);
	}

	@Override
	public void deleteUser(UUID id) {
		userCrudDatabaseRepository.deleteById(id);
	}

	@Override
	public User getUserById(UUID id) {
		
		return UserMapper.UserEntityToUserDomain(userCrudDatabaseRepository.findById(id).get());
	}	

	@Transactional
	@Override
	public void updatePassword(UUID id, String password) {
		
		userCrudDatabaseRepository.updatePassword(id, password);;
		
	}

	
	@Override
	public String findPasswordById(UUID id) {
		return userCrudDatabaseRepository.findPasswordById(id);
	}

}
