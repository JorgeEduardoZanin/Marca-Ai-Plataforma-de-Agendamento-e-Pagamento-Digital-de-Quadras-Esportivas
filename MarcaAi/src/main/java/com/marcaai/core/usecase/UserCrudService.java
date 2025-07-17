package com.marcaai.core.usecase;

import java.util.Set;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.User;
import com.marcaai.core.domain.group.UserDomainGrouping;
import com.marcaai.core.exception.UserCrudException;
import com.marcaai.core.exception.enums.ExceptionUserCrudType;
import com.marcaai.core.port.in.UserCrudUseCase;
import com.marcaai.core.port.out.internal.UserCrudRepository;
import com.marcaai.core.usecase.utils.ValidateId;

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
	public UserDomainGrouping updateUser(UUID id, User user, Address addres) {
		
		ValidateId.validateUUIDId(id);

		user.setId(id);
		User updateUser = userCrudRepository.updateUser(user);
		Address address = addressService.updateAddress(addres, updateUser.getAddress().getId());
		
		return new UserDomainGrouping(updateUser, address);
	}

	@Override
	public void deleteUser(UUID id) {
		
		ValidateId.validateUUIDId(id);
		userCrudRepository.deleteUser(id);
	}

	@Override
	public UserDomainGrouping getUserById(UUID id) {		
		
		ValidateId.validateUUIDId(id);
		var user = userCrudRepository.getUserById(id);
		var address = addressService.findById(user.getAddress().getId());
		return new UserDomainGrouping(user, address);
	}
	
	@Override
	public void updatePassword(UUID id, String newPassword){
		
		ValidateId.validateUUIDId(id);
		String oldPassword = userCrudRepository.findPasswordById(id);
		
		if(passwordEncoder.matches(newPassword, oldPassword)) {
			throw new UserCrudException(ExceptionUserCrudType.NEW_PASSWORD_SAME_AS_PREVIOUS_ONE);
		}
		
		userCrudRepository.updatePassword(id,  passwordEncoder.encode(newPassword));
		
	}

}
