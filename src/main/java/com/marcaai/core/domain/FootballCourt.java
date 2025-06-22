package com.marcaai.core.domain;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


public class FootballCourt {

	private Long id;
	
	private Enterprise enteprise;
	
	private String name;
	
	private Set<ClosedDays> closedDay = new HashSet<>();
	
	private LocalTime openingHours;
	
	private LocalTime closingTimes;
	
	private BigDecimal value;

	private Boolean available;

	private String description;
	
	private Set<Scheduling> scheduling = new HashSet<>();

	public FootballCourt(String name, Set<ClosedDays> closedDay, LocalTime openingHours, LocalTime closingTimes,
			BigDecimal value, Boolean available, String description) {
		this.name = name;
		this.closedDay = closedDay;
		this.openingHours = openingHours;
		this.closingTimes = closingTimes;
		this.value = value;
		this.available = available;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enterprise getEnteprise() {
		return enteprise;
	}

	public void setEnteprise(Enterprise enteprise) {
		this.enteprise = enteprise;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ClosedDays> getClosedDay() {
		return closedDay;
	}

	public void setClosedDay(Set<ClosedDays> closedDay) {
		this.closedDay = closedDay;
	}

	public LocalTime getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(LocalTime openingHours) {
		this.openingHours = openingHours;
	}

	public LocalTime getClosingTimes() {
		return closingTimes;
	}

	public void setClosingTimes(LocalTime closingTimes) {
		this.closingTimes = closingTimes;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Scheduling> getScheduling() {
		return scheduling;
	}

	public void setScheduling(Set<Scheduling> scheduling) {
		this.scheduling = scheduling;
	}

	


}
