package com.marcaai.core.exception;

import java.util.List;

import com.marcaai.core.exception.enums.ExceptionSchedulingType;

public class SchedulingException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final ExceptionSchedulingType exceptionSchedulingType;
	private final List<String> overlappingSchedules;

	public SchedulingException(ExceptionSchedulingType exceptionSchedulingType, List<String> overlappingSchedules) {
		super(exceptionSchedulingType.getMessage());
		this.exceptionSchedulingType = exceptionSchedulingType;
		this.overlappingSchedules = overlappingSchedules;
	}

	public ExceptionSchedulingType getExceptionSchedulingType() {
		return exceptionSchedulingType;
	}

	public List<String> getOverlappingSchedules() {
		return overlappingSchedules;
	}
	
}
