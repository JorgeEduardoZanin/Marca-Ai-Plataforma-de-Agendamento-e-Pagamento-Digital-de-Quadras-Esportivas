package com.marcaai.adapter.dto.response.companyowner;

import java.time.LocalDate;

public record CompanyOwnerResponse(String name, String phoneNumber, String cpf, String email, LocalDate dateOfBirth) {

}
