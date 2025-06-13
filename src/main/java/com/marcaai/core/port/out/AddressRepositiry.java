package com.marcaai.core.port.out;

import java.util.UUID;

import com.marcaai.core.domain.Address;

public interface AddressRepositiry {

	void createAddress(Address address);
	
	Address updateAddress(Address address);
	
	Address findByUserId (UUID id);
	
}
