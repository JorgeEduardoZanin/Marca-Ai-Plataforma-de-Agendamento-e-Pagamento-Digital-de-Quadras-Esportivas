package com.marcaai.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.marcaai.core.port.out.AddressRepositiry;
import com.marcaai.core.usecase.AddressService;

@Configuration
public class AddressConfig {

	@Bean
    public AddressService addressService(AddressRepositiry addressRepositiry) {
        return new AddressService(addressRepositiry);
    }
	
}
