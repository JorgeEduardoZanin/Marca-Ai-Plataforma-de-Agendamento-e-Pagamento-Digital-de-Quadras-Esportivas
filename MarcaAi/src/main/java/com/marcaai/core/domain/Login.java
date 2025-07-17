package com.marcaai.core.domain;

import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Login {
	
	private UUID id;
	private String email;
	private String password;
	private Set<Role> roles;
	private String token;
	private Long expireIn;
	private boolean partialApproved;
	
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Login() {
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
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(Long expireIn) {
		this.expireIn = expireIn;
	}

	public boolean isPartialApproved() {
		return partialApproved;
	}

	public void setPartialApproved(boolean partialApproved) {
		this.partialApproved = partialApproved;
	}

	public boolean isLoginCorrect(PasswordEncoder passwordEncoder, Login login) {
        return passwordEncoder.matches(login.getPassword(), this.password);
    }
}
