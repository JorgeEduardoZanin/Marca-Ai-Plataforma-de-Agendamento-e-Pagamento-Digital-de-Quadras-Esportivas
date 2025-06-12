package com.marcaai.adapter.out.database;

import org.springframework.stereotype.Component;

import com.marcaai.adapter.mapper.AddressMapper;
import com.marcaai.adapter.out.database.repository.AddressDatabaseRepository;
import com.marcaai.core.domain.Address;
import com.marcaai.core.port.out.AddressRepositiry;

@Component
public class AddressAdapter implements AddressRepositiry{

	private final AddressDatabaseRepository addressDatabaseRepository;

	public AddressAdapter(AddressDatabaseRepository addressDatabaseRepository) {
		this.addressDatabaseRepository = addressDatabaseRepository;
	}

	@Override
	public void createAddress(Address address) {
		addressDatabaseRepository.save(AddressMapper.createAddressDomainToAddressEntity(address));
	}

	@Override
	public Address updateAddress(Address address) {
		
		var addressEntity = addressDatabaseRepository.findByUserEntityId(address.getUser().getId());
			if(addressEntity.isEmpty()) {
				return null;
			}
		
		address = AddressMapper.AddressEntityToAdressDomain(addressDatabaseRepository.save(
				AddressMapper.updateAddressDomainToAddressEntity(address, addressEntity.get())));
		
		return address;
	}

	
	
}
