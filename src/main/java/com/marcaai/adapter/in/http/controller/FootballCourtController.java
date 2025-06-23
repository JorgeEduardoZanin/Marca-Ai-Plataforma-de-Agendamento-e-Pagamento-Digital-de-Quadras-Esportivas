package com.marcaai.adapter.in.http.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.footballcourt.FootballCourtRequest;
import com.marcaai.adapter.dto.response.footballcourt.FootballCourtResponse;
import com.marcaai.adapter.mapper.FootballCourtMapper;
import com.marcaai.core.port.in.FootballCourtUseCase;

@RestController
@RequestMapping("/football/court")
public class FootballCourtController {

	private final FootballCourtUseCase footballCourtUseCase;

	public FootballCourtController(FootballCourtUseCase footballCourtUseCase) {
		this.footballCourtUseCase = footballCourtUseCase;
	}
	
	public ResponseEntity<FootballCourtResponse> create(@RequestBody FootballCourtRequest footballCourtRequest, JwtAuthenticationToken token){
		var footballCourtDomain = footballCourtUseCase.create(FootballCourtMapper.footballCourtRequestToFootballCourtDomain(footballCourtRequest), UUID.fromString(token.getName()));
		return ResponseEntity.ok(FootballCourtMapper.footballCourtDomainToFootBallCourtResponse(footballCourtDomain));
	}
	
}
