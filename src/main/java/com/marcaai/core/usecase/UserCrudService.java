package com.marcaai.core.usecase;

import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.User;
import com.marcaai.core.exception.UserCrudException;
import com.marcaai.core.exception.enums.ExceptionUserCrudType;
import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.RoleRepository;
import com.marcaai.core.port.out.UserCrudRepository;

public class UserCrudService implements UserCrudUseCase{

	private final UserCrudRepository userCrudRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public UserCrudService(UserCrudRepository userCrudRepository, BCryptPasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.userCrudRepository = userCrudRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public void createUser(User user) {
		
		Role role = roleRepository.findByName(Role.Values.BASIC.name());
		
		user.setRoles(Set.of(role));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userCrudRepository.createUser(user);
		
	}

	@Override
	public User updateUser(UUID id, User user) {
		
		validateId(id);

		user.setId(id);
		User updateUser = userCrudRepository.updateUser(user);
		
		return updateUser;
	}

	@Override
	public void deleteUser(UUID id) {
		
		validateId(id);
		userCrudRepository.deleteUser(id);
	}

	@Override
	public User getUserById(UUID id) {		
		
		validateId(id);
		return userCrudRepository.getUserById(id);
	}
	
	@Override
	public void updatePassword(UUID id, String newPassword){
		
		validateId(id);
		String oldPassword = userCrudRepository.findPasswordById(id);
		
		String newHashedPassword = passwordEncoder.encode(newPassword);
		
		if(passwordEncoder.matches(newPassword, oldPassword)) {
			throw new UserCrudException(ExceptionUserCrudType.NEW_PASSWORD_SAME_AS_PREVIOUS_ONE);
		}
		
		userCrudRepository.updatePassword(id, newHashedPassword);
		
	}
	
	public void validateId(UUID id) {
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");      
		}
	}
}
