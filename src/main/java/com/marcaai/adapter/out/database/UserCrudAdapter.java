package com.marcaai.adapter.out.database;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.UserMapper;
import com.marcaai.adapter.out.database.entity.UserEntity;
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
	public void createUser(User user) {
		UserMapper.UserEntitytoUserDomain(userCrudDatabaseRepository.save(UserMapper.toUserEntity(user)));
	}

	@Override
	public User updateUser(User user) {
		
		UserEntity findUserById = userCrudDatabaseRepository.findById(user.getId())
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
		
		user.setId(findUserById.getId());
		
		var userDomain = userCrudDatabaseRepository.saveAndFlush(UserMapper.UpdateUserEntityToUserEntity(user, findUserById));
		
		return UserMapper.UserEntitytoUserDomain(userDomain);
	}

	@Override
	public Map<String, String> deleteUser(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public Map<String, String> updatePassword(UUID id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
