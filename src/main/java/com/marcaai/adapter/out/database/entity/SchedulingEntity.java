package com.marcaai.adapter.out.database.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_scheduling")
public class SchedulingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "football_court_id")
	private FootballCourtEntity footballCourtEntity;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	@CreationTimestamp
	private LocalDateTime creationTimestamp;

	public SchedulingEntity() {
	}

	public SchedulingEntity(FootballCourtEntity footballCourtEntity, LocalDateTime startTime, LocalDateTime endTime) {
		this.footballCourtEntity = footballCourtEntity;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FootballCourtEntity getFootballCourtEntity() {
		return footballCourtEntity;
	}

	public void setFootballCourtEntity(FootballCourtEntity footballCourtEntity) {
		this.footballCourtEntity = footballCourtEntity;
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

	public LocalDateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(LocalDateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	

}

