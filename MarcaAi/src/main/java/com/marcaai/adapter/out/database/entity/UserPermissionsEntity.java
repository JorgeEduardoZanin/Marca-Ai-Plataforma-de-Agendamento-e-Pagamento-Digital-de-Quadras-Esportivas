package com.marcaai.adapter.out.database.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user_permissions")
public class UserPermissionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private LocalDateTime emailVerifiedAt;
	
	private LocalDateTime sendigCodeIn;
	
	private Boolean emailVerified;
	
	private String emailVerificationCode;
	
	public UserPermissionsEntity(LocalDateTime sendigCodeIn, Boolean emailVerified, String emailVerificationCode) {
		this.sendigCodeIn = sendigCodeIn;
		this.emailVerified = emailVerified;
		this.emailVerificationCode = emailVerificationCode;
	}

	public UserPermissionsEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getEmailVerifiedAt() {
		return emailVerifiedAt;
	}

	public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getEmailVerificationCode() {
		return emailVerificationCode;
	}

	public void setEmailVerificationCode(String emailVerificationCode) {
		this.emailVerificationCode = emailVerificationCode;
	}

	public LocalDateTime getSendigCodeIn() {
		return sendigCodeIn;
	}

	public void setSendigCodeIn(LocalDateTime sendigCodeIn) {
		this.sendigCodeIn = sendigCodeIn;
	}
	
}
