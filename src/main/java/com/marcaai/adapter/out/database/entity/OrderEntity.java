package com.marcaai.adapter.out.database.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.marcaai.core.domain.enums.PaymentMethod;
import com.marcaai.core.domain.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_order")
public class OrderEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@OneToMany(mappedBy = "orderEntity")
	private List<SchedullingEntity> schedulingEntity;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private EnterpriseEntity enterpriseEntity;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private UserEntity userEntity;
	
	@Column(nullable = false)
	private BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "VARCHAR(20)")
	private PaymentStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(20)")
	private PaymentMethod paymentMethod;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	private LocalDateTime updateAt;
	
	@Size(max = 400)
	@Column(columnDefinition = "TEXT") 
	private String description;

	public OrderEntity(EnterpriseEntity enterpriseEntity, UserEntity userEntity, BigDecimal value, PaymentStatus status) {
		this.enterpriseEntity = enterpriseEntity;
		this.userEntity = userEntity;
		this.value = value;
		this.status = status;
	}

	public OrderEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SchedullingEntity> getSchedulingEntity() {
		return schedulingEntity;
	}

	public void setSchedulingEntity(List<SchedullingEntity> schedulingEntity) {
		this.schedulingEntity = schedulingEntity;
	}

	public EnterpriseEntity getEnterpriseEntity() {
		return enterpriseEntity;
	}

	public void setEnterpriseEntity(EnterpriseEntity enterpriseEntity) {
		this.enterpriseEntity = enterpriseEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
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
