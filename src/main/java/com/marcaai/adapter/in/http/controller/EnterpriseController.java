package com.marcaai.adapter.in.http.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.marcaai.adapter.dto.grouping.request.CreateEnterpriseRequestGrouping;
import com.marcaai.adapter.dto.grouping.request.UpdateEnterpriseRequestGrouping;
import com.marcaai.adapter.dto.grouping.response.EnterpriseResponseGrouping;
import com.marcaai.adapter.dto.grouping.response.enterprise.EnterprisePaginationGroupResponse;
import com.marcaai.adapter.dto.grouping.response.enterprise.UpdateEnterpriseResponseGrouping;
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
	/*
	-
	-
	-
	POST METHODS	
	-
	-
	*/
	@PostMapping
	public ResponseEntity<Map<String, String>> create(@RequestBody CreateEnterpriseRequestGrouping createEnterprise) {
		
		enterpriseUseCase.create(CompanyOwnerMapper.companyOwnerRequestToCompanyOwnerDomain(createEnterprise.companyOwner()), 
				EnterpriseMapper.createEnterpriseRequestToEnterpriseDomain(createEnterprise.enterprise()), 
				AddressMapper.addressRequestToAddressDomain(createEnterprise.address()));
		return ResponseEntity.ok(Map.of("message: ", "Empresa registrada com sucesso. Em até 3 dias úteis, um administrador entrará em contato para informar se a solicitação foi aprovada."));
	}
	/*
	-
	-
	-
	GET METHODS	
	-
	-
	*/
	@GetMapping
	public ResponseEntity<EnterpriseResponseGrouping> findById(JwtAuthenticationToken token){
		var enterpriseResponse = enterpriseUseCase.findById(UUID.fromString(token.getName()));
		
		var responseGroup = new EnterpriseResponseGrouping(AddressMapper.addressDomainToAddressResponse(enterpriseResponse.address()),
				EnterpriseMapper.enterpriseDomainToEnterpriseResponse(enterpriseResponse.enterprise()),
				CompanyOwnerMapper.companyOwnerDomainToCompanyOwnerResponse(enterpriseResponse.companyOwner()));
		
		return ResponseEntity.ok(responseGroup);		
	}
	
	@GetMapping
	public ResponseEntity<EnterprisePaginationGroupResponse> findAllPaginated(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		
		var enterprise = enterpriseUseCase.findAllPaginated(pageSize, pageSize);
		
		return ResponseEntity.ok(new EnterprisePaginationGroupResponse(EnterpriseMapper.enterpriseDomainListToEnterpriseSummaryResponseList(enterprise.enterpriseList()), pageSize, page, enterprise.totalElements(), enterprise.totalPages()));
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
	public ResponseEntity<UpdateEnterpriseResponseGrouping> update(@RequestBody UpdateEnterpriseRequestGrouping enterpriseGrouping, JwtAuthenticationToken token){
		var request = enterpriseUseCase.update(EnterpriseMapper.updateEnterpriseRequestToEnterpriseDomain(enterpriseGrouping.enterprise()),
				UUID.fromString(token.getName()),
				AddressMapper.addressRequestToAddressDomain(enterpriseGrouping.address()));
		
		var response = new UpdateEnterpriseResponseGrouping(AddressMapper.addressDomainToAddressResponse(request.address()),
				EnterpriseMapper.enterpriseDomainToEnterpriseResponse(request.enterprise()));
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, String>> updatePassword(JwtAuthenticationToken token, @RequestBody String password){
		enterpriseUseCase.updatePassword(UUID.fromString(token.getName()), password);
		
		return ResponseEntity.ok(Map.of("message:", " Senha atualizada com sucesso."));
	}
	/*
	-
	-
	-
	DELETE METHODS	
	-
	-
	*/
	@DeleteMapping
	public ResponseEntity<Map<String, String>> delete(JwtAuthenticationToken token){
		enterpriseUseCase.delete(UUID.fromString(token.getName()));
		return ResponseEntity.ok(Map.of("message:", "Empresa deletada com sucesso !"));
	}
	
}
