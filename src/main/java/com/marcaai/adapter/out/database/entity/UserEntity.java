package com.marcaai.adapter.out.database.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "tb_user")
public class UserEntity {

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
	
	@Column(name = "state", nullable = false, columnDefinition = "CHAR(2)") 
	private String state;
	
	@Column(nullable = false, length = 255)
	private String adress;
	
	@Column(nullable = false, length = 10)
	private String adress_number;
	
	@Column(nullable = false, length = 100)
	private String city;
	
	@Column(name = "CEP", nullable = false, columnDefinition = "CHAR(8)") 
	private String CEP;

	@Column(nullable = false, length = 100)
	private String neighborhood;
	
	@Column(nullable = false, length = 100)
	private String complement;
	
	@Past
	@Column(nullable = false)
	private LocalDate date_of_birth;
	
	@Column(nullable = false, length = 255)
	private String password;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime creation_date;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<RoleEntity> roles;
	
	public UserEntity() {
	}

	public UserEntity( String name, String phone_number, String cpf, String email, String state, String adress,
		String adress_number, String city, String cEP, String neighborhood, String complement,
		LocalDate date_of_birth, String password) {
		
		this.name = name;
		this.phone_number = phone_number;
		this.cpf = cpf;
		this.email = email;
		this.state = state;
		this.adress = adress;
		this.adress_number = adress_number;
		this.city = city;
		CEP = cEP;
		this.neighborhood = neighborhood;
		this.complement = complement;
		this.date_of_birth = date_of_birth;
		this.password = password;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getAdress_number() {
		return adress_number;
	}

	public void setAdress_number(String adress_number) {
		this.adress_number = adress_number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(LocalDateTime creation_date) {
		this.creation_date = creation_date;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
}
