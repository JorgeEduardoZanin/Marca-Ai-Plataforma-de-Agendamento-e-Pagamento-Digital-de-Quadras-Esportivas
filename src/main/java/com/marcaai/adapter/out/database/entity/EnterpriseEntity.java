package com.marcaai.adapter.out.database.entity;

import java.util.Set;
import java.util.UUID;
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
	private String corporate_reason;
	
	@Column(nullable = false, length = 190)
	private String fantasy_name;
	
	@Column(name = "cnpj", nullable = false, columnDefinition = "CHAR(14)", unique = true) 
	private String cnpj;
	
	@Column(nullable = false, length = 190, unique = true)
	private String email;
	
	@Column(nullable = false, length = 255)
	private String password;
	
	@Column(nullable = false, length = 16)
	private String phone_number;
	
	@Column(nullable = false, length = 20)
	private String state_registration;
	
	@Column(nullable = false, length = 20)
	private String municipal_registration;
	
	private boolean approved;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<RoleEntity> roles;
	
	@ManyToOne
	@JoinColumn(name = "company_owner_id", nullable = false)
	private CompanyOwnerEntity company_owner;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressEntity addressEntity;
	
	public EnterpriseEntity() {
	}

	public EnterpriseEntity(String corporate_reason, String fantasy_name, String cnpj, String email, String password, String phone_number, String state_registration) {
		this.corporate_reason = corporate_reason;
		this.fantasy_name = fantasy_name;
		this.cnpj = cnpj;
		this.email = email;
		this.password = password;
		this.phone_number = phone_number;
		this.state_registration = state_registration;

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCorporate_reason() {
		return corporate_reason;
	}

	public void setCorporate_reason(String corporate_reason) {
		this.corporate_reason = corporate_reason;
	}

	public String getFantasy_name() {
		return fantasy_name;
	}

	public void setFantasy_name(String fantasy_name) {
		this.fantasy_name = fantasy_name;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getState_registration() {
		return state_registration;
	}

	public void setState_registration(String state_registration) {
		this.state_registration = state_registration;
	}

	public String getMunicipal_registration() {
		return municipal_registration;
	}

	public void setMunicipal_registration(String municipal_registration) {
		this.municipal_registration = municipal_registration;
	}

	public CompanyOwnerEntity getCompany_owner() {
		return company_owner;
	}

	public void setCompany_owner(CompanyOwnerEntity company_owner) {
		this.company_owner = company_owner;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}
	
}
