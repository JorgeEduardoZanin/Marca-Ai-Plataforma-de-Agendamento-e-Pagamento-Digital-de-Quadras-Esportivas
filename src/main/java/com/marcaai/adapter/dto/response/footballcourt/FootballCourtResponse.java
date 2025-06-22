package com.marcaai.adapter.dto.response.footballcourt;

import java.math.BigDecimal;
import java.time.LocalTime;

public record FootballCourtResponse(
		String name, 
		LocalTime openingHours, 
		LocalTime closingTimes, 
		BigDecimal value, 
		Boolean available, 
		String description
		) {

}
