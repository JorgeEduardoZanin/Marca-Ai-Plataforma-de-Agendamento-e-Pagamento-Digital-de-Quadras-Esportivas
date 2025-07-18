package com.marcaai.adapter.in.http.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.out.integration.dto.request.VerificationMail;
import com.marcaai.adapter.out.integration.producer.EmailProducer;


@RestController
@RequestMapping("/mail")
public class EmailController {

	
	private final EmailProducer emailProducer;

	public EmailController(EmailProducer emailProducer) {
		super();
		this.emailProducer = emailProducer;
	}
	
	@PostMapping
	public ResponseEntity<Map<String, String>> sendMail(@RequestBody VerificationMail mail){
		emailProducer.sendEmailVerification(mail.to(), mail.code());
		return ResponseEntity.ok(Map.of("message: ", "email enviado"));
	}
	
}
