package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionRoleType {

	ROLE_NOT_FOUND("A role não foi encontrada.", HttpStatus.BAD_REQUEST);
	
	private final String message;
	private final HttpStatus httpStatus;
	
	private ExceptionRoleType(String message, HttpStatus httpStatus) {
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
