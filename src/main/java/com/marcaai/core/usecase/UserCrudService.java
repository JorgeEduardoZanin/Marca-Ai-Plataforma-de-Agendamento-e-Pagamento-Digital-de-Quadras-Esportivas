package com.marcaai.core.usecase;

import java.util.Set;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.User;
import com.marcaai.core.domain.group.UserAndAddressGrouping;
import com.marcaai.core.exception.UserCrudException;
import com.marcaai.core.exception.enums.ExceptionUserCrudType;
import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.UserCrudRepository;

public class UserCrudService implements UserCrudUseCase{

	private final AddressService addressService;
	private final RoleService roleService;
	private final UserCrudRepository userCrudRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserCrudService(RoleService roleService, UserCrudRepository userCrudRepository, BCryptPasswordEncoder passwordEncoder, AddressService addressService) {
		this.roleService = roleService;
		this.userCrudRepository = userCrudRepository;
		this.passwordEncoder = passwordEncoder;
		this.addressService = addressService;
	}

	@Override
	public void createUser(User user, Address address) {
		
		Role role = roleService.findRoleByName(Role.Values.BASIC.name());
		
		user.setRoles(Set.of(role));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		var addressId = addressService.createAddress(address);
		address.setId(addressId);
		user.setAddress(address);
		userCrudRepository.createUser(user);
		
	}

	@Override
	public UserAndAddressGrouping updateUser(UUID id, User user, Address addres) {
		
		validateId(id);

		user.setId(id);
		User updateUser = userCrudRepository.updateUser(user);
		Address address = addressService.updateAddress(addres, updateUser.getAddress().getId());
		
		return new UserAndAddressGrouping(updateUser, address);
	}

	@Override
	public void deleteUser(UUID id) {
		
		validateId(id);
		userCrudRepository.deleteUser(id);
	}

	@Override
	public UserAndAddressGrouping getUserById(UUID id) {		
		
		validateId(id);
		var user = userCrudRepository.getUserById(id);
		var address = addressService.findById(user.getAddress().getId());
		return new UserAndAddressGrouping(user, address);
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
