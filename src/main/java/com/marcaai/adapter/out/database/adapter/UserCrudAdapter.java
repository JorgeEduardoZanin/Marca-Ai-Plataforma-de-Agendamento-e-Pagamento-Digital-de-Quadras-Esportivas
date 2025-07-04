package com.marcaai.adapter.out.database.adapter;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marcaai.adapter.mapper.UserMapper;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.adapter.out.database.repository.UserCrudDatabaseRepository;
import com.marcaai.core.domain.User;
import com.marcaai.core.exception.UserCrudException;
import com.marcaai.core.exception.enums.ExceptionUserCrudType;
import com.marcaai.core.port.out.internal.UserCrudRepository;



@Component
@Transactional(rollbackFor = UserCrudException.class)
public class UserCrudAdapter implements UserCrudRepository {

	private final UserCrudDatabaseRepository userCrudDatabaseRepository;

	public UserCrudAdapter(UserCrudDatabaseRepository userCrudDatabaseRepository) {
		this.userCrudDatabaseRepository = userCrudDatabaseRepository;
	}

	@Override
	public Long createUser(User user) {
		
		UserEntity userEntity = userCrudDatabaseRepository.save(UserMapper.toUserEntity(user));
		return userEntity.getAddressEntity().getId();
	}

	@Override
	public User updateUser(User user) {
		
		UserEntity findUserById = userCrudDatabaseRepository.findById(user.getId())
				.orElseThrow(() -> new UserCrudException(ExceptionUserCrudType.USER_NOT_FOUND));
		
		user.setId(findUserById.getId());
		
		var userEntity = userCrudDatabaseRepository.saveAndFlush(UserMapper.UpdateUserDomainToUserEntity(user, findUserById));
		
		return UserMapper.UserEntityToUserDomain(userEntity);
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
