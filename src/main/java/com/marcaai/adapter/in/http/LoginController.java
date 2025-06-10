package com.marcaai.adapter.in.http;

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
	
	@PostMapping
	public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest){
		System.out.println(loginRequest.email());
		var login = loginUseCase.login(LoginMapper.LoginRequestToLoginDomain(loginRequest));
		return ResponseEntity.status(HttpStatus.OK).body(LoginMapper.loginDomainToLoginResponse(login));
	}
	
}
