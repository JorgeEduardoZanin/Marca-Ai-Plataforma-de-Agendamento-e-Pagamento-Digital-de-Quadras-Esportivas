package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionUserCrudType {

	NEW_PASSWORD_SAME_AS_PREVIOUS_ONE("A senha nova tem de ser diferente da antiga.", HttpStatus.CONFLICT),
	USER_NOT_FOUND("O usuário não foi encontrado.", HttpStatus.NOT_FOUND);
	
	private final String message;
	private final HttpStatus httpStatus;
	
	private ExceptionUserCrudType(String message, HttpStatus httpStatus) {
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
