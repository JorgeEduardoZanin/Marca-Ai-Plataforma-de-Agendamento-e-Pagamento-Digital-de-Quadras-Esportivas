package com.marcaai.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class User {

	private String name;
	
	private String phone_number;
	
	private String cpf;
	
	private String email;
	
	private String state;
	
	private String adress;
	
	private String adress_number;
	
	private String city;
	
	private String CEP;
	
	private String neighborhood;
	
	private String complement;
	
	private LocalDate date_of_birth;
	
	private String password;
	
	private LocalDateTime creation_date;

	public User(String name, String phone_number, String cpf, String email, String state, String adress,
			String adress_number, String city, String CEP, String neighborhood, String complement,
			LocalDate date_of_birth, String password) {

		this.name = name;
		this.phone_number = phone_number;
		this.cpf = cpf;
		this.email = email;
		this.state = state;
		this.adress = adress;
		this.adress_number = adress_number;
		this.city = city;
		this.CEP = CEP;
		this.neighborhood = neighborhood;
		this.complement = complement;
		this.date_of_birth = date_of_birth;
		this.password = password;
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

	public void setCEP(String CEP) {
		this.CEP = CEP;
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

	@Override
	public int hashCode() {
		return Objects.hash(CEP, adress, adress_number, city, complement, cpf, creation_date, date_of_birth, email,
				name, neighborhood, password, phone_number, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(CEP, other.CEP) && Objects.equals(adress, other.adress)
				&& Objects.equals(adress_number, other.adress_number) && Objects.equals(city, other.city)
				&& Objects.equals(complement, other.complement) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(creation_date, other.creation_date)
				&& Objects.equals(date_of_birth, other.date_of_birth) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(neighborhood, other.neighborhood)
				&& Objects.equals(password, other.password) && Objects.equals(phone_number, other.phone_number)
				&& Objects.equals(state, other.state);
	}
	
}
