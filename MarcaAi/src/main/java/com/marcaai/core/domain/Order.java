package com.marcaai.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.marcaai.core.domain.enums.PaymentMethod;
import com.marcaai.core.domain.enums.PaymentStatus;

public class Order {
	
	private Long id;
	
	private List<Schedulling> schedulings;
	
	private Enterprise enterprise;
	
	private User user;

	private BigDecimal value;
	
	private PaymentStatus status;
	
	private PaymentMethod paymentMethod;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updateAt;
	
	private String description;

	public Order(BigDecimal value, PaymentMethod paymentMethod) {
		this.value = value;
		this.paymentMethod = paymentMethod;
	}

	public Order() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Schedulling> getSchedulings() {
		return schedulings;
	}

	public void setSchedulings(List<Schedulling> schedulings) {
		this.schedulings = schedulings;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
}
