package com.marcaai.application.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("MarcaAi plataforma de agendamento e pagamento digital de quadras esportivas")
	                        .description("Projeto back-end de agendamento para quadras de society com pagamento online integrado.")
	                        .contact(new Contact().name("Jorge Eduardo de Souza").email("jorgedevsoftawre@hotmail.com"))
	                        .version("1.0.0"));
	    }
	
}
