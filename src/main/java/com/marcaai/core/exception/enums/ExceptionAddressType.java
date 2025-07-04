package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionAddressType {

	ADDRESS_NOT_FOUD("O endereco nao foi encontrado!", HttpStatus.NOT_FOUND);
	
	private final String message;
	private final HttpStatus httpStatus;
	
	private ExceptionAddressType(String message, HttpStatus httpStatus) {
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
