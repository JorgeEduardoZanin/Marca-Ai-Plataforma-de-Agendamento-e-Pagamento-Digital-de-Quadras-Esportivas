package com.marcaai.adapter.out.database.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_adress")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private UserEntity userEntity;
	
	public AddressEntity() {
	}

	public AddressEntity(String state, String adress, String adress_number, String city, String CEP, String neighborhood, String complement, UserEntity userEntity) {
		this.state = state;
		this.adress = adress;
		this.adress_number = adress_number;
		this.city = city;
		this.CEP = CEP;
		this.neighborhood = neighborhood;
		this.complement = complement;
		this.userEntity = userEntity;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CEP, adress, adress_number, city, complement, neighborhood, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressEntity other = (AddressEntity) obj;
		return Objects.equals(CEP, other.CEP) && Objects.equals(adress, other.adress)
				&& Objects.equals(adress_number, other.adress_number) && Objects.equals(city, other.city)
				&& Objects.equals(complement, other.complement) && Objects.equals(neighborhood, other.neighborhood)
				&& Objects.equals(state, other.state);
	}
	
	
	
	
}
