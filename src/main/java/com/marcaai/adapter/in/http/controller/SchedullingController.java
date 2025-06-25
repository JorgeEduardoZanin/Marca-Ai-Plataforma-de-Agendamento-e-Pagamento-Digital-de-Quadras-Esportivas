package com.marcaai.adapter.in.http.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcaai.adapter.dto.request.schedulling.SchedullingRequest;
import com.marcaai.adapter.dto.response.schedulling.SchedullingResponse;

@RestController
@RequestMapping("/football/court/{footballCourtId}/schedulling")
public class SchedullingController {
	
	@PostMapping
	public ResponseEntity<Set<SchedullingResponse>> create (@PathVariable Long footballCourtId, @RequestBody List<SchedullingRequest> schedullings){
		
	}

}
