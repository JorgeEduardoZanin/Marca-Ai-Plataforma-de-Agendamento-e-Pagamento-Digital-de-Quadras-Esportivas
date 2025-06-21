package com.marcaai.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcaai.core.port.out.internal.RoleRepository;
import com.marcaai.core.usecase.RoleService;


@Configuration
public class RoleConfig {

	@Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleService(roleRepository);
    }
}
