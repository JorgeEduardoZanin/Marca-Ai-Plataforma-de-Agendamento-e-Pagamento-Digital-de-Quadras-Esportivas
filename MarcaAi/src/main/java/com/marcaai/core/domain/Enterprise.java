package com.marcaai.core.domain;

import java.util.Set;
import java.util.UUID;

public class Enterprise{

	private UUID id;
	
	private String corporateReason;
	
	private String fantasyName;
	
	private String cnpj;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	private String stateRegistration;
	
	private String municipalRegistration;
	
	private boolean fullyApproved;;
	
	private boolean partialApproved;
	
	private Address address;
	
	private Set<Role> roles;
	
	private CompanyOwner companyOwner;
	
	private UserPermissions userPermissions;

	public Enterprise() {
	}


	public Enterprise(String corporateReason, String fantasyName, String cnpj, String email, String password, String phoneNumber, String stateRegistration) {
		this.corporateReason = corporateReason;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.stateRegistration = stateRegistration;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCorporateReason() {
		return corporateReason;
	}

	public void setCorporateReason(String corporateReason) {
		this.corporateReason = corporateReason;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	public String getMunicipalRegistration() {
		return municipalRegistration;
	}

	public void setMunicipalRegistration(String municipalRegistration) {
		this.municipalRegistration = municipalRegistration;
	}

	public boolean isFullyApproved() {
		return fullyApproved;
	}

	public void setFullyApproved(boolean fullyApproved) {
		this.fullyApproved = fullyApproved;
	}

	public boolean isPartialApproved() {
		return partialApproved;
	}

	public void setPartialApproved(boolean partialApproved) {
		this.partialApproved = partialApproved;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public CompanyOwner getCompanyOwner() {
		return companyOwner;
	}

	public void setCompanyOwner(CompanyOwner companyOwner) {
		this.companyOwner = companyOwner;
	}


	public UserPermissions getUserPermissions() {
		return userPermissions;
	}


	public void setUserPermissions(UserPermissions userPermissions) {
		this.userPermissions = userPermissions;
	}
}
	