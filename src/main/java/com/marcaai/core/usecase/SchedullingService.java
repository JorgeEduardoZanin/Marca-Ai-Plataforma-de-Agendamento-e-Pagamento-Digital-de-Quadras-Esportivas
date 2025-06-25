package com.marcaai.core.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.marcaai.core.domain.Schedulling;
import com.marcaai.core.port.in.SchedullingUseCase;

public class SchedullingService implements SchedullingUseCase{

	@Override
	public Set<Schedulling> create(Set<Schedulling> schedullings) {
		
		schedullings = schedullings.stream()
		.peek(s -> s.setEndTime(s.getStartTime().plusMinutes(s.getDuration())))
		.filter(s -> s.getStartTime().isAfter(s.getEndTime()))
		.collect(Collectors.toSet());
		
	List<Schedulling> list = new ArrayList<>(schedullings);
			for(int i = 1; i < list.size(); i++) {
				if(list.get(i-1).getEndTime().isBefore(list.get(i).getStartTime())) {
					continue;
				}
				
				System.out.println("erro");
			}
	
	
		
		
		return null;
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
	public Schedulling update(Schedulling schedulling, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
