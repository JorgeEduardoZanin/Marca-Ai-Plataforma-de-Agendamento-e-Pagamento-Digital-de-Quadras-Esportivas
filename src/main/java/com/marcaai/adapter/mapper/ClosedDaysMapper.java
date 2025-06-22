package com.marcaai.adapter.mapper;

import java.time.DayOfWeek;

import com.marcaai.core.domain.ClosedDays;
import com.marcaai.core.domain.FootballCourt;

public class ClosedDaysMapper {

	public static ClosedDays toClosedDaysDomain(DayOfWeek dayOfWeek, FootballCourt footballCount) {
		var response = new ClosedDays(dayOfWeek);
		response.setFootballCourt(footballCount);
		return response;
		
	}
	
	
}
