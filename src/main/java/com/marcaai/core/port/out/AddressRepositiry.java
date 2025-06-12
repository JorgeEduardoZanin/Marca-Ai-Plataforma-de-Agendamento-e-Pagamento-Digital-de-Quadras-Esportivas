package com.marcaai.core.port.out;

import com.marcaai.core.domain.Address;

public interface AddressRepositiry {

	void createAddress(Address address);
	
	Address updateAddress(Address address);
	
}
