package com.marcaai.adapter.dto.request.schedulling;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SchedullingRequest(
		@NotNull @Future LocalDateTime startTime,
		@NotNull @Min(1) Integer duration
		) {

}
