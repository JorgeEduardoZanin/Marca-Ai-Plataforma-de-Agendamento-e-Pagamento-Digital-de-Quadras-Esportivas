package com.marcaai.core.usecase;

import java.util.UUID;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.User;
import com.marcaai.core.port.out.AddressRepositiry;

public class AddressService {

	private final AddressRepositiry addressRepository;

	public AddressService(AddressRepositiry addressRepository) {
		this.addressRepository = addressRepository;
	}

	public void createAddress(Address address, UUID idUser) {
		
		User user = new User();
		user.setId(idUser);
		
		address.setUser(user);
		addressRepository.createAddress(address);
	}
	
	public Address updateAddress(Address address, User user) {
		
		address.setUser(user);
		return addressRepository.updateAddress(address);
	}
	
	public Address findById(UUID id) {
		
		return addressRepository.findByUserId(id);
	}
	
}
