package com.marcaai.adapter.in.http;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.UserCrudRequest;
import com.marcaai.adapter.dto.response.UserCrudResponse;
import com.marcaai.adapter.mapper.UserMapper;
import com.marcaai.core.port.in.UserCrudUseCase;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserCrudUseCase userCrudUseCase;

	public UserController(UserCrudUseCase userCrudUseCase) {
		this.userCrudUseCase = userCrudUseCase;
	}
	
	@PostMapping
	public ResponseEntity<UserCrudResponse> createUser(@RequestBody UserCrudRequest userCrudRequest, JwtAuthenticationToken token){
		System.out.println(UUID.fromString(token.getName()));
		var userResponse = UserMapper.toUserResponse(userCrudUseCase.createUser(UserMapper.toUserDomain(userCrudRequest)));
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
		
	}
	
	

}
