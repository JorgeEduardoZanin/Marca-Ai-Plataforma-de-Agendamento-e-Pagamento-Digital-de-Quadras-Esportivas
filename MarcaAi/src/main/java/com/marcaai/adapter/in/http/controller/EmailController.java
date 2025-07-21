package com.marcaai.adapter.in.http.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.email.EmailVerificationRequest;
import com.marcaai.core.port.in.EmailUseCase;


@RestController
@RequestMapping("/email")
public class EmailController {

	
	private final EmailUseCase emailUseCase;

	public EmailController(EmailUseCase emailUseCase) {
		this.emailUseCase = emailUseCase;
	}
	
	@PostMapping
	public ResponseEntity<Map<String, String>> emailVerificaion(@RequestBody EmailVerificationRequest emailVerificationRequest){
		emailUseCase.emailVerification(emailVerificationRequest.email(), emailVerificationRequest.code());
		return ResponseEntity.ok(Map.of("message: ", "Email verificado com sucesso, o login ja esta liberado."));
	}
	
}
