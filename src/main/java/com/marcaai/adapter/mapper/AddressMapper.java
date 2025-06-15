package com.marcaai.adapter.mapper;

import com.marcaai.adapter.dto.request.usercrud.CreateUserCrudRequest;
import com.marcaai.adapter.dto.request.usercrud.UpdateUserCrudRequest;
import com.marcaai.adapter.dto.response.address.AddressResponse;
import com.marcaai.adapter.out.database.entity.AddressEntity;
import com.marcaai.adapter.out.database.entity.UserEntity;
import com.marcaai.core.domain.Address;

public class AddressMapper {

	public static Address createUserCrudRequestToAdressDomain(CreateUserCrudRequest createUserCrudRequest) {
		return new Address(createUserCrudRequest.state(),
				createUserCrudRequest.adress(),
				createUserCrudRequest.adress_number(),
				createUserCrudRequest.city(),
				createUserCrudRequest.CEP(),
				createUserCrudRequest.neighborhood(),
				createUserCrudRequest.complement()
				);
	}
	
	public static Address updateUserCrudRequestToAdressDomain(UpdateUserCrudRequest updateUserCrudRequest) {
		return new Address(updateUserCrudRequest.state(),
				updateUserCrudRequest.adress(),
				updateUserCrudRequest.adress_number(),
				updateUserCrudRequest.city(),
				updateUserCrudRequest.CEP(),
				updateUserCrudRequest.neighborhood(),
				updateUserCrudRequest.complement()
				);
	}
	
	public static AddressEntity createAddressDomainToAddressEntity(Address addressDomain) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(addressDomain.getUser().getId());
		
		return new AddressEntity(addressDomain.getState(),
				addressDomain.getAdress(),
				addressDomain.getAdress_number(),
				addressDomain.getCity(),
				addressDomain.getCEP(),
				addressDomain.getNeighborhood(),
				addressDomain.getComplement());
	}
	
	
	public static AddressEntity updateAddressDomainToAddressEntity(Address addressDomain, AddressEntity addressEntity) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(addressDomain.getUser().getId());
		
		var addressSave = new AddressEntity(addressDomain.getState() != null ? addressDomain.getState() : addressEntity.getState(),
				addressDomain.getAdress() != null ? addressDomain.getAdress() : addressEntity.getAdress(),
				addressDomain.getAdress_number() != null ? addressDomain.getAdress_number() : addressEntity.getAdress_number(),
				addressDomain.getCity() != null ? addressDomain.getCity() : addressEntity.getCity(),
				addressDomain.getCEP() != null ? addressDomain.getCEP() : addressEntity.getCEP(),
				addressDomain.getNeighborhood() != null ? addressDomain.getNeighborhood() : addressEntity.getNeighborhood(),
				addressDomain.getComplement() != null ? addressDomain.getComplement() : addressEntity.getComplement());
		
		addressSave.setId(addressEntity.getId());
		
		return addressSave;
		
	}
	
	public static Address AddressEntityToAdressDomain(AddressEntity addressEntity) {
		return new Address(addressEntity.getState(),
				addressEntity.getAdress(),
				addressEntity.getAdress_number(),
				addressEntity.getCity(),
				addressEntity.getCEP(),
				addressEntity.getNeighborhood(),
				addressEntity.getComplement()
				);
	}
	
	public static AddressResponse AddressDomainToAddressResponse(Address address) {
		return new AddressResponse(address.getState(),
				address.getAdress(),
				address.getAdress_number(),
				address.getCity(),
				address.getCEP(),
				address.getNeighborhood(),
				address.getComplement());
	}
	
}
