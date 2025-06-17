package com.marcaai.core.port.out;

import com.marcaai.core.domain.Address;

public interface AddressRepositiry {

	Long createAddress(Address address);
	
	Address updateAddress(Address address, Long id);
	
	Address findById (Long id);
	
}
