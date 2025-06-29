package com.marcaai.adapter.in.http.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.login.LoginRequest;
import com.marcaai.adapter.dto.response.login.LoginResponse;
import com.marcaai.adapter.mapper.LoginMapper;
import com.marcaai.core.port.in.LoginUseCase;



@RestController
@RequestMapping("/login")
public class LoginController {

	private final LoginUseCase loginUseCase;

	public LoginController(LoginUseCase loginUseCase) {
		this.loginUseCase = loginUseCase;
	}
	
	@PostMapping("/user")
	public ResponseEntity<LoginResponse> loginUser (@RequestBody LoginRequest loginRequest){
		var login = loginUseCase.userLogin(LoginMapper.LoginRequestToLoginDomain(loginRequest));
		return ResponseEntity.status(HttpStatus.OK).body(LoginMapper.loginDomainToLoginResponse(login));
	}
	
	@PostMapping("/enterprise")
	public ResponseEntity<LoginResponse> loginEnterprise(@RequestBody LoginRequest loginRequest){
		var login = loginUseCase.enterpriseLogin(LoginMapper.LoginRequestToLoginDomain(loginRequest));
		return ResponseEntity.status(HttpStatus.OK).body(LoginMapper.loginDomainToLoginResponse(login));
	}
	
}
