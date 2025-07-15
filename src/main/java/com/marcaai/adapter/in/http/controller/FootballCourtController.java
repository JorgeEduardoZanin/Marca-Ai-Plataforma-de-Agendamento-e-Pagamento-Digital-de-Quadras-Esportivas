package com.marcaai.adapter.in.http.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.grouping.response.footballcourt.FootballCourtPaginationGroupResponse;
import com.marcaai.adapter.dto.request.footballcourt.FootballCourtRequest;
import com.marcaai.adapter.dto.response.footballcourt.FootballCourtResponse;
import com.marcaai.adapter.mapper.FootballCourtMapper;
import com.marcaai.core.port.in.FootballCourtUseCase;

@RestController
@RequestMapping("/football-court")
public class FootballCourtController {

	private final FootballCourtUseCase footballCourtUseCase;

	public FootballCourtController(FootballCourtUseCase footballCourtUseCase) {
		this.footballCourtUseCase = footballCourtUseCase;
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
	public ResponseEntity<FootballCourtResponse> create(@RequestBody FootballCourtRequest footballCourtRequest, JwtAuthenticationToken token){
		var footballCourtDomain = footballCourtUseCase.create(FootballCourtMapper.footballCourtRequestToFootballCourtDomain(footballCourtRequest), UUID.fromString(token.getName()));
		return ResponseEntity.status(HttpStatus.CREATED).body((FootballCourtMapper.footballCourtDomainToFootBallCourtResponse(footballCourtDomain)));
				
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
	public ResponseEntity<FootballCourtResponse> findById(@PathVariable Long id, JwtAuthenticationToken token){
		var footballCourtDomain = footballCourtUseCase.findById(id, UUID.fromString(token.getName()));
		return ResponseEntity.ok(FootballCourtMapper.footballCourtDomainToFootBallCourtResponse(footballCourtDomain));
	}
	
	@GetMapping
	public ResponseEntity<FootballCourtPaginationGroupResponse> findAllPaginate(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, JwtAuthenticationToken token){
		
			var domainList = footballCourtUseCase.findAllPaginatedByEnterprise(UUID.fromString(token.getName()), page, pageSize);
			var response = new FootballCourtPaginationGroupResponse(
					FootballCourtMapper.footballCourtDomainListToFootballCourtSummaryResponse(domainList.courtList()), 
					pageSize, 
					page, 
					domainList.totalElements(), 
					domainList.totalPages());
			
			return ResponseEntity.ok(response);
		
	}
	/*
	-
	-
	-
	PUT METHODS	
	-
	-
	*/
	@PutMapping("/{id}")
	public ResponseEntity<FootballCourtResponse> update(@RequestBody FootballCourtRequest footballCourt, @PathVariable Long id, JwtAuthenticationToken token){
		var footballCourtDomain = footballCourtUseCase.update(FootballCourtMapper.footballCourtRequestToFootballCourtDomain(footballCourt), id, UUID.fromString(token.getName()));
		return ResponseEntity.ok(FootballCourtMapper.footballCourtDomainToFootBallCourtResponse(footballCourtDomain));
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
	public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id, JwtAuthenticationToken token){
		footballCourtUseCase.delete(id, UUID.fromString(token.getName()));
		return ResponseEntity.ok(Map.of("message: ", "Campo de futebol deletado com sucesso !"));
	}
}





