package com.marcaai.adapter.out.database.adapter;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marcaai.adapter.mapper.FootballCourtMapper;
import com.marcaai.adapter.out.database.repository.FootballCourtDatabaseRepository;
import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.domain.group.FootballCourtPaginationGrouping;
import com.marcaai.core.exception.FootballCourtException;
import com.marcaai.core.exception.enums.ExceptionFootballCourtType;
import com.marcaai.core.port.out.internal.FootballCourtRepository;

@Component
@Transactional(rollbackFor = FootballCourtException.class)
public class FootballCourtAdapter implements FootballCourtRepository{

	private final FootballCourtDatabaseRepository footballCourtDatabaseRepository;
	
	public FootballCourtAdapter(FootballCourtDatabaseRepository footballCourtDatabaseRepository) {
		this.footballCourtDatabaseRepository = footballCourtDatabaseRepository;
	}

	@Override
	public FootballCourt create(FootballCourt footballCourt) {
		var footballEntity = footballCourtDatabaseRepository.save(FootballCourtMapper.footballCourtDomainToFootballCourtEntity(footballCourt));
		return FootballCourtMapper.footballCourtEntityToFootballCourtDomain(footballEntity);
	}

	@Override
	public FootballCourt findById(Long id) {
		var footballEntity = footballCourtDatabaseRepository.findById(id)
				.orElseThrow(() -> new FootballCourtException(ExceptionFootballCourtType.FOOTBALL_COURT_NOT_FOUND));
		return FootballCourtMapper.footballCourtEntityToFootballCourtDomain(footballEntity);
	}

	@Override
	public FootballCourtPaginationGrouping findAllPaginatedByEnterprise(UUID enterpriseID, int page, int pageSize) {
		var courtList = footballCourtDatabaseRepository.findAllPaginatedByEnterprise(enterpriseID ,PageRequest.of(page, pageSize, Sort.Direction.ASC, "name"));
		
		return FootballCourtMapper.footballCourtRepositoryDatabaseResponseToFootballCourtPaginationGrouping(courtList);
	}

	@Override
	public FootballCourt update(FootballCourt footballCourt, Long id) {
		var findEntity = footballCourtDatabaseRepository.findById(id)
				.orElseThrow(() -> new FootballCourtException(ExceptionFootballCourtType.FOOTBALL_COURT_NOT_FOUND));
		
		var update = footballCourtDatabaseRepository.saveAndFlush(
				FootballCourtMapper.updateFootballCourtDomainToFootballCourtEntity(footballCourt, findEntity));
		
		return FootballCourtMapper.footballCourtEntityToFootballCourtDomain(update);
	}

	@Override
	public void delete(Long id) {
		footballCourtDatabaseRepository.deleteById(id);
	}

	@Override
	public UUID findEnterpriseUUIDInFootballCourt(Long id) {
		return footballCourtDatabaseRepository.findEntepriseIdByFootballCourtId(id)
				.orElseThrow(() -> new FootballCourtException(ExceptionFootballCourtType.FOOTBALL_COURT_NO_ASSOCIATED_COMPANY));
	}

}
