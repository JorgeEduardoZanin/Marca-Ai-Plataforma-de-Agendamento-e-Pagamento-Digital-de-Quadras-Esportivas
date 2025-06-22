package com.marcaai.adapter.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.marcaai.adapter.dto.request.footballcourt.FootballCourtRequest;
import com.marcaai.core.domain.ClosedDays;
import com.marcaai.core.domain.FootballCourt;

public class FootballCourtMapper {

	public static FootballCourt footballCourtRequestToFootballCourtDomain(FootballCourtRequest footballCourtRequest) {
		
		var newFootballCourt =  new FootballCourt(footballCourtRequest.name(),
				null,
				footballCourtRequest.openingHours(),
				footballCourtRequest.closingTimes(),
				footballCourtRequest.value(),
				footballCourtRequest.available(),
				footballCourtRequest.description());
		
		Set<ClosedDays> closedDays = footballCourtRequest.daysOfWeek().stream()
				.map(ClosedDays::new)
				.peek(cd -> cd.setFootballCourt(newFootballCourt))
				.collect(Collectors.toSet());
		
		newFootballCourt.setClosedDay(closedDays);
		
		return newFootballCourt;
		
		}
	
}
