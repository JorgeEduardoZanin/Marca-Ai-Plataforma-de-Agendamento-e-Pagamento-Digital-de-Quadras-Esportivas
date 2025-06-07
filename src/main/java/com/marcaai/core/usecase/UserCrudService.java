package com.marcaai.core.usecase;

import java.util.Map;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.User;
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
	public User createUser(User user) {
		
		Role role = roleRepository.findByName(Role.Values.BASIC.name());
		
		user.setRoles(Set.of(role));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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
