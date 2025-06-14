package com.marcaai.adapter.out.database.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_administrator")
public class AdministratorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, length = 255)
	private String name;
	
	@Column(name = "cpf", nullable = false, columnDefinition = "CHAR(11)", unique = true) 
	private String cpf;
	
	@Column(nullable = false, length = 180, unique = true)
	private String email;
	
	
	@Column(nullable = false, length = 255)
	private String password;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime creation_date;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<RoleEntity> roles;
	
	

}
