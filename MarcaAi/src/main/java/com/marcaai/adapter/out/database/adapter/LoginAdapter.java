package com.marcaai.adapter.out.database.adapter;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marcaai.adapter.mapper.LoginMapper;
import com.marcaai.adapter.out.database.repository.EnterpriseDatabaseRepository;
import com.marcaai.adapter.out.database.repository.UserCrudDatabaseRepository;
import com.marcaai.core.domain.Login;
import com.marcaai.core.domain.group.LoginAndPermissionsGroup;
import com.marcaai.core.exception.LoginException;
import com.marcaai.core.exception.enums.ExceptionLoginType;
import com.marcaai.core.port.out.internal.LoginRepository;

@Component
@Transactional(rollbackFor = LoginException.class)
public class LoginAdapter implements LoginRepository {

	private final UserCrudDatabaseRepository userCrudDatabaseRepository;
	private final EnterpriseDatabaseRepository enterpriseDatabaseRepository;
	
	public LoginAdapter(UserCrudDatabaseRepository userCrudDatabaseRepository,
			EnterpriseDatabaseRepository enterpriseDatabaseRepository) {
		this.userCrudDatabaseRepository = userCrudDatabaseRepository;
		this.enterpriseDatabaseRepository = enterpriseDatabaseRepository;
	}

	@Override
	public LoginAndPermissionsGroup findByUserEmail(String email) {
		var userEntity = userCrudDatabaseRepository.findByEmail(email)
				.orElseThrow(() -> new LoginException(ExceptionLoginType.INVALID_PASSWORD_OR_EMAIL));
		
		return LoginMapper.UserEntitytoLoginDomain(userEntity);
	}

	@Override
	public LoginAndPermissionsGroup findByEnterpriseEmail(String email) {
		
		var enterpriseEntity = enterpriseDatabaseRepository.findByEmail(email)
				.orElseThrow(() -> new LoginException(ExceptionLoginType.INVALID_PASSWORD_OR_EMAIL));
		
		return LoginMapper.EnterpriseEntitytoLoginDomain(enterpriseEntity);
	}

	@Override
	public Login findByAdministratorEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
