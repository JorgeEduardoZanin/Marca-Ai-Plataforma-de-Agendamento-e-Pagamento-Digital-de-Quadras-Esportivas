package com.marcaai.adapter.in.http.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.schedulling.SchedullingRequest;
import com.marcaai.adapter.dto.response.schedulling.SchedullingResponse;
import com.marcaai.adapter.mapper.SchedullingMapper;
import com.marcaai.core.port.in.SchedulingUseCase;

@RestController
@RequestMapping("/footballcourt/{footballCourtId}/schedulling")
public class SchedullingController {
	
	private final SchedulingUseCase schedulingUseCase;
	
	public SchedullingController(SchedulingUseCase schedulingUseCase) {
		this.schedulingUseCase = schedulingUseCase;
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
	public ResponseEntity<Set<SchedullingResponse>> create (@PathVariable Long footballCourtId, @RequestBody List<SchedullingRequest> schedullings, JwtAuthenticationToken token){
		var schedullingsDomain = schedulingUseCase.create(SchedullingMapper.listSchedullingsRequestToSetListSchedullingsDomain(schedullings), footballCourtId, UUID.fromString(token.getName()));
		return ResponseEntity.status(HttpStatus.CREATED).body(SchedullingMapper.listSchedullingDomainToListSchedullingResponse(schedullingsDomain));
	}
	/*
	-
	-
	-
	GET METHODS	
	-
	-
	*/
	@GetMapping("/{id}")
	public ResponseEntity<SchedullingResponse> findById(@PathVariable Long id, @PathVariable Long footballCourtId, JwtAuthenticationToken token){
		var domain = schedulingUseCase.findById(id, footballCourtId, UUID.fromString(token.getName()));
		return ResponseEntity.ok(SchedullingMapper.schedulingDomainToSchedulingResponse(domain));
	}
	
	@GetMapping
	public ResponseEntity<Set<SchedullingResponse>> listAllByFootballCourtAndDay(@PathVariable Long footballCourtId, @RequestBody LocalDate date, JwtAuthenticationToken token){
		var domainList = schedulingUseCase.findAllByFootballCourtAndDay(footballCourtId, date, UUID.fromString(token.getName()));
		
		return ResponseEntity.ok(SchedullingMapper.listSchedullingDomainToListSchedullingResponse(domainList));
		
	}
	/*
	-
	-
	-
	DELETE METHODS	
	-
	-
	*/
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id, @PathVariable Long footballCourtId, JwtAuthenticationToken token){
		schedulingUseCase.deleteById(id, UUID.fromString(token.getName()), footballCourtId);
		
		return ResponseEntity.ok(Map.of("message", "Agendamento deletado com sucesso!"));
		
	}
	
	@DeleteMapping
	public ResponseEntity<Map<String, String>> deleteAllByFootballCourt(@PathVariable Long footballCourtId, JwtAuthenticationToken token){
	
		schedulingUseCase.deleteAllByFootballCourt(footballCourtId, UUID.fromString(token.getName()));
		
		return ResponseEntity.ok(Map.of("message", "Todos os agendamentos do campo de futebol selecionado foram deletados com sucesso !"));
	}
	
	@DeleteMapping
	public ResponseEntity<Map<String, String>> deleteAllByFootballCourtAndDate(@PathVariable Long footballCourtId, @RequestBody LocalDate date, JwtAuthenticationToken token){
		
		schedulingUseCase.deleteAllByFootballCourtAndDate(footballCourtId, date, UUID.fromString(token.getName()));
		
		return ResponseEntity.ok(Map.of("message", "Todos os agendamentos do dia "+date.getDayOfMonth()+" de "
				+date.getMonth().toString()+" de "+date.getYear()+" foram deletados com sucesso!"));
	}
	
}
