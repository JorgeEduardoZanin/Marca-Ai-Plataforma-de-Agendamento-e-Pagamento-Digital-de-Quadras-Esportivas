package com.marcaai.adapter.dto.request.login;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Validated
public record LoginRequest(@NotBlank String email, @NotBlank String password) {

}
