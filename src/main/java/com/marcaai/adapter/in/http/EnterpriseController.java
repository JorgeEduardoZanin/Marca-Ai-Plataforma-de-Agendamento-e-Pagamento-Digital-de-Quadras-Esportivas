package com.marcaai.adapter.in.http;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.marcaai.adapter.dto.grouping.request.CreateEnterpriseGrouping;
import com.marcaai.adapter.dto.grouping.response.EnterpriseResponseGrouping;
import com.marcaai.adapter.mapper.AddressMapper;
import com.marcaai.adapter.mapper.CompanyOwnerMapper;
import com.marcaai.adapter.mapper.EnterpriseMapper;
import com.marcaai.core.port.in.EnterpriseUseCase;


@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

	private final EnterpriseUseCase enterpriseUseCase;
	
	public EnterpriseController(EnterpriseUseCase enterpriseUseCase) {
		this.enterpriseUseCase = enterpriseUseCase;
	}


	@PostMapping
	public ResponseEntity<Map<String, String>> create(@RequestBody CreateEnterpriseGrouping createEnterprise) {
		
		enterpriseUseCase.create(CompanyOwnerMapper.companyOwnerRequestToCompanyOwnerDomain(createEnterprise.companyOwner()), 
				EnterpriseMapper.createEnterpriseRequestToEnterpriseDomain(createEnterprise.enterprise()), 
				AddressMapper.AddressRequestToAddressDomain(createEnterprise.address()));
		return ResponseEntity.ok(Map.of("message: ", "Empresa registrada com sucesso. Em até 3 dias úteis, um administrador entrará em contato para informar se a solicitação foi aprovada."));
	}
	
	@GetMapping
	public ResponseEntity<EnterpriseResponseGrouping> findById(JwtAuthenticationToken token){
		var enterpriseResponse = enterpriseUseCase.findById(UUID.fromString(token.getName()));
		return ResponseEntity.ok(enterpriseResponse);		
	}
	
}
