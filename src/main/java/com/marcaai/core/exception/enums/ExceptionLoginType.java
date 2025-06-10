package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionLoginType {

	INVALID_PASSWORD_OR_EMAIL("Senha ou email invalidos!", HttpStatus.UNAUTHORIZED);

	
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
