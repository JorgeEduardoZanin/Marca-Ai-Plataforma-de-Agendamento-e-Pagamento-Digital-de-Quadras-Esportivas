package com.marcaai.adapter.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.marcaai.adapter.dto.request.footballcourt.FootballCourtRequest;
import com.marcaai.adapter.dto.response.footballcourt.FootballCourtResponse;
import com.marcaai.adapter.dto.response.footballcourt.FootballCourtSummaryResponse;
import com.marcaai.adapter.out.database.dto.response.footballcourt.FootballCourtDatabaseResponse;
import com.marcaai.adapter.out.database.dto.response.footballcourt.SoccerFieldOrderDatabaseResponse;
import com.marcaai.adapter.out.database.entity.EnterpriseEntity;
import com.marcaai.adapter.out.database.entity.FootballCourtEntity;
import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.group.FootballCourtPaginationGrouping;

public class FootballCourtMapper {

	public static FootballCourt footballCourtRequestToFootballCourtDomain(FootballCourtRequest footballCourtRequest) {
		
		var newFootballCourt =  new FootballCourt(footballCourtRequest.name(),
				footballCourtRequest.daysOfWeek(),
				footballCourtRequest.openingHours(),
				footballCourtRequest.closingTimes(),
				footballCourtRequest.value(),
				footballCourtRequest.available(),
				footballCourtRequest.description());
		
		return newFootballCourt;
		
		}
	
	public static FootballCourtResponse footballCourtDomainToFootBallCourtResponse(FootballCourt footballCourt) {
		return new FootballCourtResponse(
				footballCourt.getId(),
				footballCourt.getName(),
				footballCourt.getOpeningHours(),
				footballCourt.getClosingTimes(),
				footballCourt.getValue(),
				footballCourt.getAvailable(),
				footballCourt.getDescription());
		
	}
	
	public static FootballCourt footballCourtEntityToFootballCourtDomain(FootballCourtEntity footballCourt) {
		var domain = new FootballCourt(footballCourt.getName(),
				footballCourt.getClosedDay(),
				footballCourt.getOpeningHours(),
				footballCourt.getClosingTimes(),
				footballCourt.getValue(),
				footballCourt.getAvailable(),
				footballCourt.getDescription());
		
		domain.setId(footballCourt.getId());
		
		return domain;
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
		
		footballEntity.setEnterpriseEntity(enterprise);
		System.out.println("id fc entity do enterprise entity"+ footballEntity.getEnterpriseEntity().getId());
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
	
	public static FootballCourtPaginationGrouping footballCourtRepositoryDatabaseResponseToFootballCourtPaginationGrouping(Page<FootballCourtDatabaseResponse> courtList){
		
		Enterprise enterprise = new Enterprise();
		enterprise.setId(courtList.getContent().getFirst().enterprise().getId());
		
		var domainCourtList = courtList.getContent().stream()
				.map(databaseResponse -> {
					FootballCourt footballCourt = new FootballCourt();
					footballCourt.setId(databaseResponse.id());
					footballCourt.setEnteprise(enterprise);
					footballCourt.setValue(databaseResponse.value());
					footballCourt.setName(databaseResponse.name());
					
					return footballCourt;
				})
				.toList();
		
		return new FootballCourtPaginationGrouping(domainCourtList, courtList.getTotalElements(), courtList.getTotalPages());
		
	}
	
	public static List<FootballCourtSummaryResponse> footballCourtDomainListToFootballCourtSummaryResponse(List<FootballCourt> court){
		
		return court.stream()
				.map(courtDomain -> {
					return new FootballCourtSummaryResponse(
							courtDomain.getName(),
							courtDomain.getValue(),
							courtDomain.getEnteprise().getId(),
							courtDomain.getId());
					
				})
				.toList();

	}
	
	public static List<FootballCourt> listSoccerFieldDatabaseResponseToListFootballCourtDomain(List<SoccerFieldOrderDatabaseResponse> databaseResponse){
		
		var domainList = databaseResponse.stream()
				.map(response -> {
					var domain = new FootballCourt();
					domain.setValue(response.value());
					domain.setId(response.id());
					domain.setAvailable(response.available());
					return domain;
				})
				.toList();
		
		return domainList;
	}
	
}
