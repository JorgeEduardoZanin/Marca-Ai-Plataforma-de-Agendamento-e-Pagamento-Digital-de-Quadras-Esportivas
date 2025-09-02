package com.marcaai.core.usecase;

import com.marcaai.core.domain.Address;
import com.marcaai.core.port.out.internal.AddressRepositiry;

public class AddressService {

	private final AddressRepositiry addressRepository;

	public AddressService(AddressRepositiry addressRepository) {
		this.addressRepository = addressRepository;
	}

	public Long createAddress(Address address) {
		address.setCEP(address.getCEP().replaceAll("[^\\d]", ""));
		return addressRepository.createAddress(address);
	}
	
	public Address updateAddress(Address address, Long id) {
		
		return addressRepository.updateAddress(address, id);
	}
	
	public Address findById(Long id) {
		
		return addressRepository.findById(id);
	}
	
}
