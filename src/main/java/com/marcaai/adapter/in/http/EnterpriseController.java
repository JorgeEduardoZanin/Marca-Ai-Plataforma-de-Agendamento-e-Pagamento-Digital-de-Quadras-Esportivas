package com.marcaai.adapter.in.http;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.marcaai.adapter.dto.grouping.request.CreateEnterpriseGrouping;
import com.marcaai.adapter.mapper.EnterpriseMapper;
import com.marcaai.core.port.in.EnterpriseUseCase;


@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

	private final EnterpriseUseCase enterpriseUseCase;
	
	public EnterpriseController(EnterpriseUseCase enterpriseUseCase) {
		this.enterpriseUseCase = enterpriseUseCase;
	}


	public ResponseEntity<Map<String, String>> create(@RequestBody CreateEnterpriseGrouping createEnterprise) {
		
		enterpriseUseCase.create(null, EnterpriseMapper.createEnterpriseRequestToEnterpriseDomain(createEnterprise.enterprise()));
		return ResponseEntity.ok(Map.of("message: ", "Empresa registrada com sucesso. Em até 3 dias úteis, um administrador entrará em contato para informar se a solicitação foi aprovada."));
	}
	
}
