package com.marcaai.adapter.out.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.marcaai.adapter.out.integration.client.CheckCnpjClient;
import com.marcaai.adapter.out.integration.dto.response.CnpjValidationResponse;
import com.marcaai.core.domain.group.CnpjGrouping;
import com.marcaai.core.port.out.external.CheckCnpjRepository;



@Component
public class CheckCnpjIntegration implements CheckCnpjRepository{

	private final CheckCnpjClient cnpjClient;
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckCnpjIntegration.class);
	
	public CheckCnpjIntegration(CheckCnpjClient cnpjClient) {
		this.cnpjClient = cnpjClient;
	}

	@Override
	public CnpjGrouping checkCnpj(String cnpj) {	
		var response = CnpjValidationResponse.cnpjResponseToCnpjDomain(cnpjClient.getValidatedCnpj(cnpj));
		LOGGER.info("Consumindo api receita empreasa: "+ response.cnpj());
		LOGGER.info("Consumindo api receita empreasa: "+ response.address()); 
		return response;
	}

}
