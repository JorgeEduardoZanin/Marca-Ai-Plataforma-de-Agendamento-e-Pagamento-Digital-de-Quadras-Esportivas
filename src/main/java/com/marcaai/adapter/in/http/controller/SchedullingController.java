package com.marcaai.adapter.in.http.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.schedulling.SchedullingRequest;
import com.marcaai.adapter.dto.response.schedulling.SchedullingResponse;
import com.marcaai.adapter.mapper.SchedullingMapper;
import com.marcaai.core.port.in.SchedullingUseCase;

@RestController
@RequestMapping("/football/court/{footballCourtId}/schedulling")
public class SchedullingController {
	
	private final SchedullingUseCase schedullingUseCase;
	
	public SchedullingController(SchedullingUseCase schedullingUseCase) {
		this.schedullingUseCase = schedullingUseCase;
	}


	@PostMapping
	public ResponseEntity<Set<SchedullingResponse>> create (@PathVariable Long footballCourtId, @RequestBody List<SchedullingRequest> schedullings){
		var schedullingsDomain = schedullingUseCase.create(SchedullingMapper.listSchedullingsRequestToSetListSchedullingsDomain(schedullings), footballCourtId);
		return ResponseEntity.status(HttpStatus.CREATED).body(SchedullingMapper.listSchedullingDomainToListSchedullingResponse(schedullingsDomain));
	}

}
