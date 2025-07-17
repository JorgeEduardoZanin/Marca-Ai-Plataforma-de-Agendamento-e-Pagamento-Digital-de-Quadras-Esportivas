package com.marcaai.core.exception;

import com.marcaai.core.exception.enums.ExceptionFootballCourtType;

public class FootballCourtException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final ExceptionFootballCourtType exceptionFootballCourtType;

	public FootballCourtException(ExceptionFootballCourtType exceptionFootballCourtType) {
		super(exceptionFootballCourtType.getMessage());
		this.exceptionFootballCourtType = exceptionFootballCourtType;
	}

	public ExceptionFootballCourtType getExceptionFootballCourtType() {
		return exceptionFootballCourtType;
	}
	
}
