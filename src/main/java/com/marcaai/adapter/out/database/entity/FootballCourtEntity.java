package com.marcaai.adapter.out.database.entity;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_football_court")
public class FootballCourtEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private EnterpriseEntity entepriseEntity;
	
	@Column(nullable = false, length = 180)
	private String name;
	
	@ElementCollection
	@CollectionTable(name = "tb_closed_days",  joinColumns = @JoinColumn(name = "football_court_id"))
	@Column(name = "day_of_week", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Set<DayOfWeek> closedDay = new HashSet<>();
	
	@Column(nullable = false)
	private LocalTime openingHours;
	
	@Column(nullable = false)
	private LocalTime closingTimes;
	
	@Column(nullable = false)
	private BigDecimal value;
	
	@Column(nullable = false)
	private Boolean available;
	
	@Size(max = 400)
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@CreationTimestamp
	private LocalDateTime creationDate;
	
	@OneToMany(mappedBy = "footballCourtEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SchedullingEntity> schedulingEntity = new HashSet<>();

	public FootballCourtEntity(String name, Set<DayOfWeek> closedDay, LocalTime openingHours,
			LocalTime closingTimes, BigDecimal value, Boolean available, @Size(max = 400) String description) {
		this.name = name;
		this.closedDay = closedDay;
		this.openingHours = openingHours;
		this.closingTimes = closingTimes;
		this.value = value;
		this.available = available;
		this.description = description;
	}

	public FootballCourtEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnterpriseEntity getEntepriseEntity() {
		return entepriseEntity;
	}

	public void setEntepriseEntity(EnterpriseEntity entepriseEntity) {
		this.entepriseEntity = entepriseEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<DayOfWeek> getClosedDay() {
		return closedDay;
	}

	public void setClosedDay(Set<DayOfWeek> closedDay) {
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Set<SchedullingEntity> getSchedulingEntity() {
		return schedulingEntity;
	}

	public void setSchedulingEntity(Set<SchedullingEntity> schedulingEntity) {
		this.schedulingEntity = schedulingEntity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(available, closedDay, closingTimes, creationDate, entepriseEntity, id, name, openingHours,
				value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FootballCourtEntity other = (FootballCourtEntity) obj;
		return Objects.equals(available, other.available) && closedDay == other.closedDay
				&& Objects.equals(closingTimes, other.closingTimes) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(entepriseEntity, other.entepriseEntity) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(openingHours, other.openingHours)
				&& Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "FootballCourt [id=" + id + ", entepriseEntity=" + entepriseEntity + ", name=" + name + ", closedDay="
				+ closedDay + ", openingHours=" + openingHours + ", closingTimes=" + closingTimes + ", value=" + value
				+ ", available=" + available + ", creationDate=" + creationDate + "]";
	}
	
}
