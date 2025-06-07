package com.marcaai.core.usecase;

import java.time.Instant;
import java.util.stream.Collectors;

import org.apache.kafka.common.Uuid;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.marcaai.core.domain.Login;
import com.marcaai.core.domain.Role;
import com.marcaai.core.port.in.LoginUseCase;
import com.marcaai.core.port.out.LoginRepository;

public class LoginService implements LoginUseCase {


	
	private final LoginRepository loginRepository;
	private final JwtEncoder jwtEncoder;
	private BCryptPasswordEncoder passwordEncoder;
	
	public LoginService(LoginRepository loginRepository, JwtEncoder jwtEncoder,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.loginRepository = loginRepository;
		this.jwtEncoder = jwtEncoder;
		this.passwordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Login login(Login login) {
		
		var usuarioLogin = loginRepository.findByEmail(login.getEmail());
		
		 if (!usuarioLogin.isLoginCorrect(passwordEncoder, login)) {
	            throw new BadCredentialsException("user or password is invalid!");
	        }

	        var now = Instant.now();
	        var expiresIn = 300L;

	        var scopes = usuarioLogin.getRoles()
	                .stream()
	                .map(Role::getName)
	                .collect(Collectors.joining(" "));

	        var claims = JwtClaimsSet.builder()
	                .issuer("mybackend")
	                .subject(usuarioLogin.getId().toString())
	                .issuedAt(now)
	                .expiresAt(now.plusSeconds(expiresIn))
	                .claim("scope", scopes)
	                .build();

	        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	        
	        Login loginResponse = new Login();
	        loginResponse.setToken(jwtValue);
	        loginResponse.setExpireIn(expiresIn);
	        	      
	        return loginResponse;
	}

	
	
}
