package com.marcaai.adapter.dto.request.schedulling;

import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SchedullingRequest(
		Optional<Long> id,
		@Future(message = "O horário de início deve ser no futuro") @NotNull @Future LocalDateTime startTime,
		@NotNull @Min(1) Integer duration
		) {

}
