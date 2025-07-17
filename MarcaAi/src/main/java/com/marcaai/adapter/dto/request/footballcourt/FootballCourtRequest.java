package com.marcaai.adapter.dto.request.footballcourt;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FootballCourtRequest(
		@NotBlank String name, 
		@NotNull LocalTime openingHours, 
		@NotNull LocalTime closingTimes, 
		@NotNull BigDecimal value, 
		@NotNull Boolean available, 
		Set<DayOfWeek> daysOfWeek,
		@NotBlank String description) {

}



