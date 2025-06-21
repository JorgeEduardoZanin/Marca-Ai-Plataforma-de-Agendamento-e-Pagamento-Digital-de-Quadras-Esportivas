package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.out.internal.AddressRepositiry;
import com.marcaai.core.usecase.AddressService;

@Configuration
public class AddressConfig {

	@Bean
    public AddressService addressService(AddressRepositiry addressRepositiry) {
        return new AddressService(addressRepositiry);
    }
	
}
