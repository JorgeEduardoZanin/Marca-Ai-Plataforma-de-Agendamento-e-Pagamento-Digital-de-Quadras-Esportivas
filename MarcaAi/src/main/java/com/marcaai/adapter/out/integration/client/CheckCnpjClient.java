package com.marcaai.adapter.out.integration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.marcaai.adapter.out.integration.dto.response.CnpjValidationResponse;

@FeignClient(value = "check", url = "${api.checkcnpj}")
public interface CheckCnpjClient {
	
	@GetMapping(value = "/{cnpj}", produces = MediaType.APPLICATION_JSON_VALUE)
	CnpjValidationResponse getValidatedCnpj(@PathVariable String cnpj);
	

}
