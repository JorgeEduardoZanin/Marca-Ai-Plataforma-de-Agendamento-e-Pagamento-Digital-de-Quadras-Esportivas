package com.marcaai.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.marcaai.core.domain.enums.PaymentMethod;
import com.marcaai.core.domain.enums.PaymentStatus;

public class Payment {

	private String id;
	
	private String name;
	
	private String email;
	
	private String phoneNumber;
	
	private String cpf;
	
	private PaymentMethod paymentMethod;
	
	private BigDecimal amount;
	
	private PaymentStatus paymentStatus;
	
	private String pixCode;
	
	private String pixCodeBase64;
	
	private LocalDateTime exipiresAt;
	
	private LocalDateTime createAt;

	public Payment() {
	}

	public Payment(String name, String email, String phoneNumber, String cpf) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cpf = cpf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPixCode() {
		return pixCode;
	}

	public void setPixCode(String pixCode) {
		this.pixCode = pixCode;
	}

	public String getPixCodeBase64() {
		return pixCodeBase64;
	}

	public void setPixCodeBase64(String pixCodeBase64) {
		this.pixCodeBase64 = pixCodeBase64;
	}

	public LocalDateTime getExipiresAt() {
		return exipiresAt;
	}

	public void setExipiresAt(LocalDateTime exipiresAt) {
		this.exipiresAt = exipiresAt;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	
	
}