package com.marcaai.adapter.mapper;

import java.util.Optional;

import com.marcaai.adapter.dto.request.footballcourt.FootballCourtRequest;
import com.marcaai.adapter.dto.response.footballcourt.FootballCourtResponse;
import com.marcaai.adapter.out.database.entity.EnterpriseEntity;
import com.marcaai.adapter.out.database.entity.FootballCourtEntity;
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
		
		newFootballCourt.setClosedDay(footballCourtRequest.daysOfWeek());
		
		return newFootballCourt;
		
		}
	
	public static FootballCourtResponse footballCourtDomainToFootBallCourtResponse(FootballCourt footballCourt) {
		return new FootballCourtResponse(footballCourt.getName(),
				footballCourt.getOpeningHours(),
				footballCourt.getClosingTimes(),
				footballCourt.getValue(),
				footballCourt.getAvailable(),
				footballCourt.getDescription());
		
	}
	
	public static FootballCourt footballCourtEntityToFootballCourtDomain(FootballCourtEntity footballCourt) {
		return new FootballCourt(footballCourt.getName(),
				footballCourt.getClosedDay(),
				footballCourt.getOpeningHours(),
				footballCourt.getClosingTimes(),
				footballCourt.getValue(),
				footballCourt.getAvailable(),
				footballCourt.getDescription());
	}
	
	public static FootballCourtEntity footballCourtDomainToFootballCourtEntity(FootballCourt footballCourt) {
		var enterprise = new EnterpriseEntity();
		enterprise.setId(footballCourt.getEnteprise().getId());
		
		var footballEntity = new FootballCourtEntity(footballCourt.getName(),
				footballCourt.getClosedDay(),
				footballCourt.getOpeningHours(),
				footballCourt.getClosingTimes(),
				footballCourt.getValue(),
				footballCourt.getAvailable(),
				footballCourt.getDescription());
		
		footballEntity.setEntepriseEntity(enterprise);
		
		return footballEntity;
	}
	
	
	public static FootballCourtEntity updateFootballCourtDomainToFootballCourtEntity(FootballCourt footballCourtDomain, FootballCourtEntity footballCourtEntity) {
		
		Optional.ofNullable(footballCourtDomain.getName()).ifPresent(footballCourtEntity::setName);
		Optional.ofNullable(footballCourtDomain.getClosedDay()).ifPresent(footballCourtEntity::setClosedDay);
		Optional.ofNullable(footballCourtDomain.getOpeningHours()).ifPresent(footballCourtEntity::setOpeningHours);
		Optional.ofNullable(footballCourtDomain.getClosingTimes()).ifPresent(footballCourtEntity::setClosingTimes);
		Optional.ofNullable(footballCourtDomain.getDescription()).ifPresent(footballCourtEntity::setDescription);
		Optional.ofNullable(footballCourtDomain.getAvailable()).ifPresent(footballCourtEntity::setAvailable);
		Optional.ofNullable(footballCourtDomain.getValue()).ifPresent(footballCourtEntity::setValue);
		
		return footballCourtEntity;
		
	}
	
}
