package com.marcaai.adapter.in.http;

import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.marcaai.adapter.dto.grouping.UserAndAddressResponse;
import com.marcaai.adapter.dto.request.usercrud.CreateUserCrudRequest;
import com.marcaai.adapter.dto.request.usercrud.UpdatePasswordCrudRequest;
import com.marcaai.adapter.dto.request.usercrud.UpdateUserCrudRequest;
import com.marcaai.adapter.mapper.AddressMapper;
import com.marcaai.adapter.mapper.UserMapper;
import com.marcaai.core.port.in.UserCrudUseCase;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserCrudUseCase userCrudUseCase;

	public UserController(UserCrudUseCase userCrudUseCase, JwtDecoder jwtDecoder) {
		this.userCrudUseCase = userCrudUseCase;
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
	public ResponseEntity<Map<String, String>> createUser( @Valid @RequestBody CreateUserCrudRequest createUserCrudRequest){
		userCrudUseCase.createUser(UserMapper.toUserDomain(createUserCrudRequest), AddressMapper.createUserCrudRequestToAdressDomain(createUserCrudRequest));
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message:", " Usuário criado com sucesso."));
		
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
	public ResponseEntity<UserAndAddressResponse> findUserById(JwtAuthenticationToken token){
		var userResponse = userCrudUseCase.getUserById(UUID.fromString(token.getName()));
		var response = new UserAndAddressResponse(
				UserMapper.UserToUpdateUserCrudResponse(userResponse.user()),
				AddressMapper.addressDomainToAddressResponse(userResponse.adress()));
		
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
	@PutMapping()
	public ResponseEntity<UserAndAddressResponse> updateUser(@RequestBody UpdateUserCrudRequest updateUserCrudRequest, JwtAuthenticationToken token){
		var userResponse = userCrudUseCase.updateUser(UUID.fromString(token.getName()), 
					UserMapper.UpdateUserCrudRequestToUserDomain(updateUserCrudRequest), 
					AddressMapper.updateUserCrudRequestToAdressDomain(updateUserCrudRequest));	
		
		var addressUserGrouping = new UserAndAddressResponse(
				UserMapper.UserToUpdateUserCrudResponse(userResponse.user()), 
				AddressMapper.addressDomainToAddressResponse(userResponse.adress()));
		
		return ResponseEntity.status(HttpStatus.OK).body(addressUserGrouping);
	}
	
	@PutMapping("/password")
	public ResponseEntity<Map<String, String>> updatePassword( @Valid @RequestBody UpdatePasswordCrudRequest userPassword, JwtAuthenticationToken token){
		userCrudUseCase.updatePassword(UUID.fromString(token.getName()), userPassword.password());
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
	public ResponseEntity<Map<String, String>> deleteUser(JwtAuthenticationToken token){
		userCrudUseCase.deleteUser(UUID.fromString(token.getName()));
		return ResponseEntity.ok(Map.of("message:", " Usuário deletado com sucesso."));
	}
	
	
	
	

}
