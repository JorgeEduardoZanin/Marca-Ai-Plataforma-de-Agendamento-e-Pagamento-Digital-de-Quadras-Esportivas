package com.marcaai.adapter.out.database.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_scheduling")
public class SchedullingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "football_court_id")
	private FootballCourtEntity footballCourtEntity;
	
	@NotNull
	@Future(message =  "O horário de início deve ser no futuro")
	private LocalDateTime startTime;
	
	@NotNull
	private LocalDateTime endTime;
	
	@NotNull
	private Integer duration;

	@NotNull
	private Boolean reserved;
	
	@CreationTimestamp
	private LocalDateTime creationTimestamp;

	public SchedullingEntity() {
	}

	public SchedullingEntity(FootballCourtEntity footballCourtEntity, LocalDateTime startTime, Integer duration, Boolean reserved) {
		this.footballCourtEntity = footballCourtEntity;
		this.startTime = startTime;
		this.duration = duration;
		this.reserved = reserved;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public LocalDateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(LocalDateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
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
		return Objects.hash(creationTimestamp, duration, footballCourtEntity, id, reserved, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchedullingEntity other = (SchedullingEntity) obj;
		return Objects.equals(creationTimestamp, other.creationTimestamp) && Objects.equals(duration, other.duration)
				&& Objects.equals(footballCourtEntity, other.footballCourtEntity) && Objects.equals(id, other.id)
				&& Objects.equals(reserved, other.reserved) && Objects.equals(startTime, other.startTime);
	}

	

}

