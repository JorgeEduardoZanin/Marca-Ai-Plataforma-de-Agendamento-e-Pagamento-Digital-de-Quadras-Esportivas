package com.marcaai.core.domain;

import java.time.LocalDateTime;

public class UserPermissions {

	private Long id;
	
	private LocalDateTime emailVerifiedAt;
	
	private LocalDateTime sendigCodeIn;
	
	private Boolean emailVerified;
	
	private String emailVerificationCode;

	public UserPermissions(LocalDateTime sendigCodeIn, Boolean emailVerified, String emailVerificationCode) {
		this.sendigCodeIn = sendigCodeIn;
		this.emailVerified = emailVerified;
		this.emailVerificationCode = emailVerificationCode;
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

	@Override
	public String toString() {
		return "UserPermissions [id=" + id + ", emailVerifiedAt=" + emailVerifiedAt + ", sendigCodeIn=" + sendigCodeIn
				+ ", emailVerified=" + emailVerified + ", emailVerificationCode=" + emailVerificationCode + "]";
	}
	
	
	
}
