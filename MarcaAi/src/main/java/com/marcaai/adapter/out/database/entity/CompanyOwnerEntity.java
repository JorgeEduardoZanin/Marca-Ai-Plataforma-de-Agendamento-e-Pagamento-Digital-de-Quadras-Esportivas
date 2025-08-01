package com.marcaai.adapter.out.database.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "tb_company_owner")
public class CompanyOwnerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, length = 255)
	private String name;
	
	@Column(nullable = false, length = 16)
	private String phone_number;
	
	@Column(name = "cpf", nullable = false, columnDefinition = "CHAR(11)", unique = true) 
	private String cpf;
	
	@Column(nullable = false, length = 180, unique = true)
	private String email;
	
	@Past
	@Column(nullable = false)
	private LocalDate date_of_birth;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime creation_date;
	
	@OneToMany(mappedBy = "company_owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EnterpriseEntity> enterprises = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressEntity addressEntity;

	public CompanyOwnerEntity() {
	}

	public CompanyOwnerEntity(UUID id, String name, String phone_number, String cpf, String email,
			@Past LocalDate date_of_birth) {
		this.id = id;
		this.name = name;
		this.phone_number = phone_number;
		this.cpf = cpf;
		this.email = email;
		this.date_of_birth = date_of_birth;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public LocalDateTime getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(LocalDateTime creation_date) {
		this.creation_date = creation_date;
	}
	
	
	
}
