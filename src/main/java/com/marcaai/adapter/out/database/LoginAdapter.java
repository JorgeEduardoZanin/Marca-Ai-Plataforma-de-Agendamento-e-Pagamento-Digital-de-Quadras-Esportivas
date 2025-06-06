package com.marcaai.adapter.out.database;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.LoginMapper;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.adapter.out.database.repository.LoginDatabaseRepository;
import com.marcaai.core.domain.Login;
import com.marcaai.core.port.out.LoginRepository;

@Component
public class LoginAdapter implements LoginRepository {

	private final LoginDatabaseRepository loginDatabaseRepository;
	
	public LoginAdapter(LoginDatabaseRepository loginDatabaseRepository) {
		this.loginDatabaseRepository = loginDatabaseRepository;
	}

	@Override
	public Login findByEmail(String email) {
		Optional<UserEntity> userEntity = loginDatabaseRepository.findByEmail(email);	
		if(userEntity.isEmpty()) {
			return null;
		}
		return LoginMapper.UserEntitytoLoginDomain(userEntity.get());
	}

}
