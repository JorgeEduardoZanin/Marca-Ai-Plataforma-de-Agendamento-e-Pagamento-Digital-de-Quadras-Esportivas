package com.marcaai.adapter.dto.request.usercrud;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordCrudRequest(@NotBlank String password) {

}
