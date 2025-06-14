package com.marcaai.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CompanyOwner {
	
		private UUID id;
		
		private String name;
		
		private String phone_number;
		
		private String cpf;
		
		private String email;
		
		private LocalDate date_of_birth;
		
		private LocalDateTime creation_date;

		public CompanyOwner() {
		}

		public CompanyOwner(UUID id, String name, String phone_number, String cpf, String email,
				LocalDate date_of_birth) {
			this.id = id;
			this.name = name;
			this.phone_number = phone_number;
			this.cpf = cpf;
			this.email = email;
			this.date_of_birth = date_of_birth;
		}

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone_number() {
			return phone_number;
		}

		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public LocalDate getDate_of_birth() {
			return date_of_birth;
		}

		public void setDate_of_birth(LocalDate date_of_birth) {
			this.date_of_birth = date_of_birth;
		}

		public LocalDateTime getCreation_date() {
			return creation_date;
		}

		public void setCreation_date(LocalDateTime creation_date) {
			this.creation_date = creation_date;
		}

}
