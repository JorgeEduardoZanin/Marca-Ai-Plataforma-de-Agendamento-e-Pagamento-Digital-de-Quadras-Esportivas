package com.marcaai.adapter.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.marcaai.adapter.dto.request.schedulling.SchedullingRequest;
import com.marcaai.adapter.dto.response.schedulling.SchedullingResponse;
import com.marcaai.adapter.out.database.entity.FootballCourtEntity;
import com.marcaai.adapter.out.database.entity.SchedullingEntity;
import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.Schedulling;

public class SchedullingMapper {

	public static Set<Schedulling> listSchedullingsRequestToSetListSchedullingsDomain(List<SchedullingRequest> schedullings){
		
		return schedullings.stream()
				.map(request -> {
					Schedulling s = new Schedulling(request.startTime(), request.duration());
					return s;
				})
				.collect(Collectors.toSet());
	
	}
	
	public static List<SchedullingEntity> listSchedullingsDomainToListSchedullingsEntity(List<Schedulling> schedullings){
		
		FootballCourtEntity footballCourt = new FootballCourtEntity();
		footballCourt.setId(schedullings.get(0).getId());
		
		return schedullings.stream()
				.map(domain -> {
					SchedullingEntity s = new SchedullingEntity(footballCourt, domain.getStartTime(), domain.getDuration(), false);
					s.setEndTime(domain.getEndTime());
					return s;
				})
				.collect(Collectors.toList());		
		
	}
	
	public static List<Schedulling> listSchedullingEntityToListSchedullingDomain(List<SchedullingEntity> schedullings){
		
		FootballCourt footballCourt = new FootballCourt();
		footballCourt.setId(schedullings.get(0).getId());
		
		return schedullings.stream()
				.map(entity -> {
					Schedulling schedullingDomain = new Schedulling(entity.getStartTime(), entity.getDuration());
					schedullingDomain.setId(entity.getId());
					schedullingDomain.setEndTime(entity.getEndTime());
					schedullingDomain.setFootballCourt(footballCourt);
					schedullingDomain.setReserved(entity.getReserved());
					return schedullingDomain;
				})
				.collect(Collectors.toList());
	}
	
	public static Set<SchedullingResponse> listSchedullingDomainToListSchedullingResponse(Set<Schedulling> schedullings){
		return schedullings.stream()
				.map(domain -> {
					SchedullingResponse response = new SchedullingResponse(
							domain.getId(), 
							domain.getFootballCourt().getId(),
							domain.getStartTime(),
							domain.getReserved(),
							domain.getEndTime(),
							domain.getDuration());
					
					return response;
				})
				.collect(Collectors.toSet());
	}
	
	public static Schedulling schedullingEntityToSchedullingDomain(SchedullingEntity schedulling) {
		var footballCourt = new FootballCourt();
		footballCourt.setId(schedulling.getFootballCourtEntity().getId());
		
		var response = new Schedulling(schedulling.getStartTime(), schedulling.getDuration());
		response.setEndTime(schedulling.getEndTime());
		response.setFootballCourt(footballCourt);
		response.setId(schedulling.getId());
		response.setReserved(schedulling.getReserved());
		
		return response;
	}
	
	public static SchedullingResponse schedulingDomainToSchedulingResponse(Schedulling schedulling) {
		
		return new SchedullingResponse(
				schedulling.getId(),
				schedulling.getFootballCourt().getId(),
				schedulling.getStartTime(),
				schedulling.getReserved(),
				schedulling.getEndTime(),
				schedulling.getDuration());
			
	}
	
}
