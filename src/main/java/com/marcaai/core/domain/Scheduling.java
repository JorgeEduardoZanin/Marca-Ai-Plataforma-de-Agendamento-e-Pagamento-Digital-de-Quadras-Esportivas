package com.marcaai.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Scheduling {

	private Long id;
	
	private FootballCourt footballCourt;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private BigDecimal value;
	
	public Scheduling() {
	}

	public Scheduling(LocalDateTime startTime, LocalDateTime endTime, BigDecimal value) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FootballCourt getFootballCourt() {
		return footballCourt;
	}

	public void setFootballCourt(FootballCourt footballCourt) {
		this.footballCourt = footballCourt;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}

