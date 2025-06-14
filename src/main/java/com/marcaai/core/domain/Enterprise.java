package com.marcaai.core.domain;

import java.util.UUID;

public class Enterprise {

	private UUID id;
	private String corporate_reason;
	private String fantasy_name;
	private String cnpj;
	private String email;
	private String password;
	private String phone_number;
	private String state_registration;
	private String municipal_registration;
	
	private CompanyOwner company_owner;

	public Enterprise(String corporate_reason, String fantasy_name, String cnpj, String email, String password, String phone_number, String state_registration, CompanyOwner company_owner) {
		this.corporate_reason = corporate_reason;
		this.fantasy_name = fantasy_name;
		this.cnpj = cnpj;
		this.email = email;
		this.password = password;
		this.phone_number = phone_number;
		this.state_registration = state_registration;
		this.company_owner = company_owner;
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

	public CompanyOwner getCompany_owner() {
		return company_owner;
	}

	public void setCompany_owner(CompanyOwner company_owner) {
		this.company_owner = company_owner;
	}
	
	
	
	
	
	
}
