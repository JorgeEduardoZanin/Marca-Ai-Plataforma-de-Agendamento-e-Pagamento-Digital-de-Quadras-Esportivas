package com.marcaai.core.usecase;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;


import com.marcaai.core.domain.Login;
import com.marcaai.core.domain.Role;
import com.marcaai.core.exception.LoginException;
import com.marcaai.core.exception.enums.ExceptionLoginType;
import com.marcaai.core.port.in.LoginUseCase;
import com.marcaai.core.port.out.internal.LoginRepository;

public class LoginService implements LoginUseCase {


	
	private final LoginRepository loginRepository;
	private final JwtEncoder jwtEncoder;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public LoginService(LoginRepository loginRepository, JwtEncoder jwtEncoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.loginRepository = loginRepository;
		this.jwtEncoder = jwtEncoder;
		this.passwordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Login userLogin(Login login) {
		
		var userLogin = loginRepository.findByUserEmail(login.getEmail());
		System.out.println(userLogin.toString());
		 if (!userLogin.login().isLoginCorrect(passwordEncoder, login)) {
	            throw new LoginException(ExceptionLoginType.INVALID_PASSWORD_OR_EMAIL);
	        }
		 
		 if(userLogin.userPermissions().getEmailVerified() == false) {
			 throw new LoginException(ExceptionLoginType.EMAIL_NOT_VERIFIED);
		 }

	        var now = Instant.now();
	        var expiresIn = 10000L;

	        var scopes = userLogin.login().getRoles()
	                .stream()
	                .map(Role::getName)
	                .collect(Collectors.joining(" "));

	        var claims = JwtClaimsSet.builder()
	                .issuer("mybackend")
	                .subject(userLogin.login().getId().toString())
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

	@Override
	public Login adminLogin(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login enterpriseLogin(Login login) {
		var enterpriseLoign = loginRepository.findByEnterpriseEmail(login.getEmail());
		
		 if (!enterpriseLoign.login().isLoginCorrect(passwordEncoder, login)) {
	            throw new LoginException(ExceptionLoginType.INVALID_PASSWORD_OR_EMAIL);
	        }
		 
		 if(enterpriseLoign.userPermissions().getEmailVerified() == false) {
			 throw new LoginException(ExceptionLoginType.EMAIL_NOT_VERIFIED);
		 }
		 
		 if(enterpriseLoign.login().isPartialApproved() == false) {
			 throw new LoginException(ExceptionLoginType.COMPANY_HAS_NOT_YET_BEEN_APPROVED);
		 }

	        var now = Instant.now();
	        var expiresIn = 300L;

	        var scopes = enterpriseLoign.login().getRoles()
	                .stream()
	                .map(Role::getName)
	                .collect(Collectors.joining(" "));

	        var claims = JwtClaimsSet.builder()
	                .issuer("mybackend")
	                .subject(enterpriseLoign.login().toString())
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
