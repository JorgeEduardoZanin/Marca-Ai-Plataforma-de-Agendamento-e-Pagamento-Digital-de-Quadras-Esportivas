package com.marcaai.adapter.in.http;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.CreateUserCrudRequest;
import com.marcaai.adapter.dto.request.UpdateUserCrudRequest;
import com.marcaai.adapter.dto.response.UpdateUserCrudResponse;
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
	public ResponseEntity<Map<String, String>> createUser(@RequestBody CreateUserCrudRequest createUserCrudRequest){
		userCrudUseCase.createUser(UserMapper.toUserDomain(createUserCrudRequest));
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message:", " Usuário criado com sucesso"));
		
	}
	
	@PutMapping
	public ResponseEntity<UpdateUserCrudResponse> updateUser(@RequestBody UpdateUserCrudRequest updateUserCrudRequest, JwtAuthenticationToken token){
		var userResponse = UserMapper.UserToUpdateUserCrudResponse(
				userCrudUseCase.updateUser(UUID.fromString(token.getName()), UserMapper.UpdateUserCrudRequestToUserDomain(updateUserCrudRequest)));	
		
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}
	
	

}
