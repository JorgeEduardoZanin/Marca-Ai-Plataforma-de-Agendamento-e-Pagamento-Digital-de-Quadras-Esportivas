package com.marcaai.adapter.out.database.adapter;

import java.util.List;

import com.marcaai.adapter.mapper.FootballCourtMapper;
import com.marcaai.adapter.out.database.repository.FootballCourtDatabaseRepository;
import com.marcaai.core.domain.FootballCourt;
import com.marcaai.core.port.out.internal.FootballCourtRepository;

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
				.orElseThrow(() -> null);
		return FootballCourtMapper.footballCourtEntityToFootballCourtDomain(footballEntity);
	}

	@Override
	public List<FootballCourt> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FootballCourt update(FootballCourt footballCourt, Long id) {
		var findEntity = footballCourtDatabaseRepository.findById(id)
				.orElseThrow(() -> null);
		
		var update = footballCourtDatabaseRepository.saveAndFlush(
				FootballCourtMapper.updateFootballCourtDomainToFootballCourtEntity(footballCourt, findEntity));
		
		return FootballCourtMapper.footballCourtEntityToFootballCourtDomain(update);
	}

	@Override
	public void delete(Long id) {
		footballCourtDatabaseRepository.deleteById(id);
	}

}
