package com.marcaai.core.domain;

import java.time.DayOfWeek;

public class ClosedDays {
	
	private Long id;
	
	private DayOfWeek dayOfWeek;
	
	private FootballCourt footballCourt;

	public ClosedDays() {
	}

	public ClosedDays(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public FootballCourt getFootballCourt() {
		return footballCourt;
	}

	public void setFootballCourt(FootballCourt footballCourt) {
		this.footballCourt = footballCourt;
	}
	
}
