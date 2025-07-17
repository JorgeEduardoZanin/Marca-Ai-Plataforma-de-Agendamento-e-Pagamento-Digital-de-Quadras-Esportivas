package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionCompanyOwnerType {

	COMPANY_OWNER_NOT_FOUND("Dono da empresa não encontrado", HttpStatus.NOT_FOUND);
	
	private final String message;
	private final HttpStatus httpStatus;
	
	private ExceptionCompanyOwnerType(String message, HttpStatus httpStatus) {
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
