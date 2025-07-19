package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionLoginType {

	INVALID_PASSWORD_OR_EMAIL("Senha ou email invalidos!", HttpStatus.UNAUTHORIZED),
	EMAIL_NOT_VERIFIED("O e-mail do usuário ainda não foi verificado.", HttpStatus.UNAUTHORIZED),
	COMPANY_HAS_NOT_YET_BEEN_APPROVED("A empresa ainda está aguardando aprovação.", HttpStatus.UNAUTHORIZED);


	
	private final String message;
	private final HttpStatus httpStatus;
	
	private ExceptionLoginType(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
	
	
	
}
