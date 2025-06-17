package com.marcaai.adapter.dto.request.address;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(	
		@NotBlank String state, 
		@NotBlank String adress,
		@NotBlank String adress_number,
		@NotBlank String city,
		@NotBlank String CEP, 
		@NotBlank String neighborhood, 
		@NotBlank String complement) {

}
