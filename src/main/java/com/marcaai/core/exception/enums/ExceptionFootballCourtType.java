package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionFootballCourtType {

	FOOTBALL_COURT_NOT_FOUND("Campo de futebol não encontrado.", HttpStatus.NOT_FOUND),
	FOOTBALL_COURT_NO_ASSOCIATED_COMPANY("Essa campo de futebol não tem nenhuma empresa associada.", HttpStatus.NOT_FOUND),
	UNAUTHORIZED_FOOTBALL_COURT_ACCESS("Você só pode acessar seus próprios campos de futebol.", HttpStatus.FORBIDDEN);

	
	private final String message;
	private final HttpStatus httpStatus;
	
	private ExceptionFootballCourtType(String message, HttpStatus httpStatus) {
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
