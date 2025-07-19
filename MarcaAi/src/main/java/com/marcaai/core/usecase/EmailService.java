package com.marcaai.core.usecase;

import com.marcaai.core.port.out.external.EmailRepository;

public class EmailService {

	private final EmailRepository emailRepository;

	public EmailService(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}
	
	public void sendEmailVerification(String to, String code) {
		emailRepository.sendEmailVerification(to, code);
	}
	
}
