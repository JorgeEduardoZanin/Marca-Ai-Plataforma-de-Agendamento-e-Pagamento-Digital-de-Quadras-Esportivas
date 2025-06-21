package com.marcaai.adapter.out.database.entity;

import java.time.DayOfWeek;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ClosedDaysEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private DayOfWeek dayOfWeek;
	
	@ManyToOne
	@JoinColumn(name = "enterprise_id", nullable = false)
	private EnterpriseEntity enterpriseEntity;

	public ClosedDaysEntity() {
	}

	public ClosedDaysEntity(DayOfWeek dayOfWeek) {
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
	
	
	
}
