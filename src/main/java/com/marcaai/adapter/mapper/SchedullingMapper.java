package com.marcaai.adapter.mapper;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.marcaai.adapter.dto.request.schedulling.SchedullingRequest;
import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.Schedulling;

public class SchedullingMapper {

	public static Set<Schedulling> schedullingsRequestToSchedullingsDomain(List<SchedullingRequest> schedullings, Long footballCourtid){
		
		FootballCourt f = new FootballCourt();
		f.setId(footballCourtid);
		
		Set<Schedulling> domain = schedullings.stream()
				.map(request -> {
					Schedulling s = new Schedulling(request.startTime(), request.duration());
					s.setFootballCourt(f);
					return s;
				})
				.sorted(Comparator.comparing(Schedulling::getStartTime))
				.collect(Collectors.toCollection(LinkedHashSet::new));
		
		return domain;
	}
	
}
