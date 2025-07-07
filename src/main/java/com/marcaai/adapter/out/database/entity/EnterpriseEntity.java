package com.marcaai.adapter.out.database.entity;

import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_enterprise")
public class EnterpriseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	
	@Column(nullable = false, length = 180)
	private String corporateReason;
	
	@Column(nullable = false, length = 190)
	private String fantasyName;
	
	@Column(name = "cnpj", nullable = false, columnDefinition = "CHAR(14)", unique = true) 
	private String cnpj;
	
	@Column(nullable = false, length = 190, unique = true)
	private String email;
	
	@Column(nullable = false, length = 255)
	private String password;
	
	@Column(nullable = false, length = 16)
	private String phoneNumber;
	
	@Column(nullable = false, length = 20)
	private String stateRegistration;
	
	@Column(length = 20)
	private String municipalRegistration;
	
	private boolean fullyApproved;;
	
	private boolean partialApproved;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<RoleEntity> roles;
	
	@ManyToOne
	@JoinColumn(name = "company_owner_id", nullable = false)
	private CompanyOwnerEntity company_owner;
	
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressEntity addressEntity;
	
	public EnterpriseEntity() {
	}

	public EnterpriseEntity(String corporateReason, String fantasyName, String cnpj, String email, String password,
			String phoneNumber, String stateRegistration) {
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

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public CompanyOwnerEntity getCompany_owner() {
		return company_owner;
	}

	public void setCompany_owner(CompanyOwnerEntity company_owner) {
		this.company_owner = company_owner;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

}