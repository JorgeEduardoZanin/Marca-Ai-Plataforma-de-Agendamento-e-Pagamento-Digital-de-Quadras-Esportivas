package com.marcaai.core.usecase;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.Payment;
import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.User;
import com.marcaai.core.domain.enums.PaymentMethod;
import com.marcaai.core.port.in.EmailUseCase;
import com.marcaai.core.port.out.external.EmailRepository;

public class EmailService implements EmailUseCase{

	private final EmailRepository emailRepository;
	private final EnterpriseService enterpriseService;
	private final UserCrudService userService;
	

	public EmailService(EmailRepository emailRepository, EnterpriseService enterpriseService, UserCrudService userService) {

		this.emailRepository = emailRepository;
		this.enterpriseService = enterpriseService;
		this.userService = userService;
	}

	public void sendEmailVerification(String to, String code) {
		emailRepository.sendEmailVerification(to, code);
	}

	@Override
	public void emailVerification(String email, String code) {
		
	
		

	}
	
}
