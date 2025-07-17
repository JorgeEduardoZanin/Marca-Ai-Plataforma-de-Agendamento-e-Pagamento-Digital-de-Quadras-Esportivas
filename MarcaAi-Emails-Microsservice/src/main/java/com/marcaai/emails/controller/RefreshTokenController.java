package com.marcaai.emails.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.emails.dto.request.EmailRequest;
import com.marcaai.emails.service.GmailService;

@RestController
@RequestMapping("/email")
public class RefreshTokenController {

	private final GmailService gmailService;
	
	public RefreshTokenController(GmailService gmailService) {
		this.gmailService = gmailService;
	}

	@GetMapping("/oauth2callback")
	public String getCode(@RequestParam String code) {
		System.out.println(code);
	    return "Seu código é: " + code;
	}
	
	@PostMapping
	public ResponseEntity<Map<String, String>> sendEmail(@RequestBody EmailRequest emailRequest) throws Exception{
		gmailService.sendEmail(emailRequest.to(), emailRequest.subject(), emailRequest.bodyText());
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message: ","email enviado"));
		
	}

	
}
