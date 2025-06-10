package com.marcaai.adapter.out.database;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.LoginMapper;
import com.marcaai.adapter.out.database.repository.LoginDatabaseRepository;
import com.marcaai.core.domain.Login;
import com.marcaai.core.exception.LoginException;
import com.marcaai.core.exception.enums.ExceptionLoginType;
import com.marcaai.core.port.out.LoginRepository;

@Component
public class LoginAdapter implements LoginRepository {

	private final LoginDatabaseRepository loginDatabaseRepository;
	
	public LoginAdapter(LoginDatabaseRepository loginDatabaseRepository) {
		this.loginDatabaseRepository = loginDatabaseRepository;
	}

	@Override
	public Login findByEmail(String email) {
		var userEntity = loginDatabaseRepository.findByEmail(email)
				.orElseThrow(() -> new LoginException(ExceptionLoginType.INVALID_PASSWORD_OR_EMAIL));
		
		return LoginMapper.UserEntitytoLoginDomain(userEntity);
	}

}
