package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionSchedulingType {

	  SCHEDULING_NOT_FOUND("Agendamento não encontrado", HttpStatus.NOT_FOUND),
	  NO_SCHEDULINGS_FOR_COURT("Nenhum agendamento encontrado para esta quadra", HttpStatus.NOT_FOUND),
	  CANNOT_SCHEDULE_ON_CLOSED_DAY("Não é possível agendar em dia em que o campo está fechado", HttpStatus.CONFLICT),
	  SCHEDULING_CONFLICT("Já existe um agendamento neste horário", HttpStatus.CONFLICT),
	  REQUIRE_AT_LEAST_ONE_SCHEDULE("É necessário enviar ao menos um horário", HttpStatus.BAD_REQUEST),
	  UNAUTHORIZED_SCHEDULING_ACCESS("Você só pode acessar seus próprios agendamentos", HttpStatus.FORBIDDEN);
	
	private final String message;
	private final HttpStatus httpStatus;
	
	private ExceptionSchedulingType(String message, HttpStatus httpStatus) {
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
