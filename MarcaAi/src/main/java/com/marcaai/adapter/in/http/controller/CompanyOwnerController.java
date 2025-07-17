package com.marcaai.adapter.in.http.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.marcaai.adapter.dto.request.companyowner.UpdateCompanyOwnerRequest;
import com.marcaai.adapter.dto.response.companyowner.CompanyOwnerResponse;
import com.marcaai.adapter.mapper.CompanyOwnerMapper;
import com.marcaai.core.port.in.CompanyOwnerUseCase;

@RestController
@RequestMapping("/companyOnwer")
public class CompanyOwnerController {

	private final CompanyOwnerUseCase companyOwnerUseCase;
	
	public CompanyOwnerController(CompanyOwnerUseCase companyOwnerUseCase) {
		this.companyOwnerUseCase = companyOwnerUseCase;
	}
	/*
	-
	-
	-
	PUT METHODS	
	-
	-
	*/
	@PutMapping
	public ResponseEntity<CompanyOwnerResponse> update (@RequestBody UpdateCompanyOwnerRequest companyOwner, JwtAuthenticationToken token){
		var response = companyOwnerUseCase.update(UUID.fromString(token.getName()), CompanyOwnerMapper.updateCompanyOwnerRequestToCompanyOwnerDomain(companyOwner));
		return ResponseEntity.ok(CompanyOwnerMapper.companyOwnerDomainToCompanyOwnerResponse(response));
		
	}
	
	
}
