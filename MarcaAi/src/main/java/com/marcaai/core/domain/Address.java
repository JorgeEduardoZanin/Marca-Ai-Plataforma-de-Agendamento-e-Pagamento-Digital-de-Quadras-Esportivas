package com.marcaai.core.domain;

import java.util.Objects;


public class Address{

	private Long id;
	
	private String state;
	
	private String adress;
	
	private String adress_number;
	
	private String city;
	
	private String CEP;

	private String neighborhood;
	
	private String complement;
	
	public Address() {
	}

	public Address(String state, String adress, String adress_number, String city, String CEP, String neighborhood, String complement) {
		this.state = state;
		this.adress = adress;
		this.adress_number = adress_number;
		this.city = city;
		this.CEP = CEP;
		this.neighborhood = neighborhood;
		this.complement = complement;
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
		Address other = (Address) obj;
		return Objects.equals(CEP, other.CEP) && Objects.equals(adress, other.adress)
				&& Objects.equals(adress_number, other.adress_number) && Objects.equals(city, other.city)
				&& Objects.equals(complement, other.complement) && Objects.equals(neighborhood, other.neighborhood)
				&& Objects.equals(state, other.state);
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", state=" + state + ", adress=" + adress + ", adress_number=" + adress_number
				+ ", city=" + city + ", CEP=" + CEP + ", neighborhood=" + neighborhood + ", complement=" + complement
				+ "]";
	}

	public boolean validateEnterpriseAddress(Address enterpriseAddress) {
		if(!this.state.equals(enterpriseAddress.state) || !this.city.equals(enterpriseAddress.city)
				|| !this.adress.equals(enterpriseAddress.adress)) {
			return false;
		}
		
		return true;
	}
	
}
