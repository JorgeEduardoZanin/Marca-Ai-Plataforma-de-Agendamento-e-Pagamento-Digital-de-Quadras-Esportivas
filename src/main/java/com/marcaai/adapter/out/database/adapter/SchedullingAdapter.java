package com.marcaai.adapter.out.database.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marcaai.adapter.mapper.SchedullingMapper;
import com.marcaai.adapter.out.database.repository.SchedullingDatabaseRepository;
import com.marcaai.core.domain.Schedulling;
import com.marcaai.core.port.out.internal.SchedullingRepository;

@Component
//@Transactional(rollbackFor = BusinessException.class)
public class SchedullingAdapter implements SchedullingRepository {

	private final SchedullingDatabaseRepository schedullingDatabaseRepository;
	
	public SchedullingAdapter(SchedullingDatabaseRepository schedullingDatabaseRepository) {
		this.schedullingDatabaseRepository = schedullingDatabaseRepository;
	}


	@Override
	public List<Schedulling> create(List<Schedulling> schedullings) {
		
		var listSchedullingsEntitySave = schedullingDatabaseRepository.saveAll(SchedullingMapper.listSchedullingsDomainToListSchedullingsEntity(schedullings));
		
		return SchedullingMapper.listSchedullingEntityToListSchedullingDomain(listSchedullingsEntitySave);
	}


	@Override
	public Schedulling findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Schedulling> listAllByFootballCourt(Long footballCourtId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Schedulling update(Schedulling schedulling) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllByFootballCourt(Long footballCourtId) {
		// TODO Auto-generated method stub
		
	}

}
