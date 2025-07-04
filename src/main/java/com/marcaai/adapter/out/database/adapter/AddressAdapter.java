package com.marcaai.adapter.out.database.adapter;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marcaai.adapter.mapper.AddressMapper;
import com.marcaai.adapter.out.database.repository.AddressDatabaseRepository;
import com.marcaai.core.domain.Address;
import com.marcaai.core.exception.AddressException;
import com.marcaai.core.exception.enums.ExceptionAddressType;
import com.marcaai.core.port.out.internal.AddressRepositiry;

@Component
@Transactional(rollbackFor = AddressException.class)
public class AddressAdapter implements AddressRepositiry{

	private final AddressDatabaseRepository addressDatabaseRepository;


	public AddressAdapter(AddressDatabaseRepository addressDatabaseRepository) {
		this.addressDatabaseRepository = addressDatabaseRepository;
	}

	@Override
	public Long createAddress(Address address) {
		var newAddress = addressDatabaseRepository.save(AddressMapper.createAddressDomainToAddressEntity(address));
		return newAddress.getId();
	}

	@Override
	public Address updateAddress(Address address, Long id) {
		
		var addressEntity = addressDatabaseRepository.findById(id)
				.orElseThrow(()-> new AddressException(ExceptionAddressType.ADDRESS_NOT_FOUD));
		
		address = AddressMapper.addressEntityToAdressDomain(addressDatabaseRepository.save(
				AddressMapper.updateAddressDomainToAddressEntity(address, addressEntity)));
		
		return address;
	}

	@Override
	public Address findById(Long id) {

		var address = addressDatabaseRepository.findById(id)
				.orElseThrow(() -> new AddressException(ExceptionAddressType.ADDRESS_NOT_FOUD));
		
		return AddressMapper.addressEntityToAdressDomain(address);
	}

	
	
}
