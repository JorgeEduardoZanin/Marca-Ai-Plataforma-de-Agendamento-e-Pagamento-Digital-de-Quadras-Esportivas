package com.marcaai.core.domain;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Objects;

public class Schedulling {

	private Long id;
	
	private FootballCourt footballCourt;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private Integer duration;
	
	private Boolean reserved;
	
	public Schedulling() {
	}

	public Schedulling(LocalDateTime startTime, Integer duration) {
		this.startTime = startTime;
		this.duration = duration;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
	

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, footballCourt, id, reserved, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedulling other = (Schedulling) obj;
		return Objects.equals(duration, other.duration) && Objects.equals(footballCourt, other.footballCourt)
				&& Objects.equals(id, other.id) && Objects.equals(reserved, other.reserved)
				&& Objects.equals(startTime, other.startTime);
	}

	public int compareTo(Schedulling other) {
	    return this.startTime.compareTo(other.startTime);
	}
	
	
}

