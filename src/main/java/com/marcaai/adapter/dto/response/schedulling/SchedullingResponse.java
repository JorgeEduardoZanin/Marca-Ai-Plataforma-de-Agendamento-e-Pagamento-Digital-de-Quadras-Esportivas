package com.marcaai.adapter.dto.response.schedulling;

import java.time.LocalDateTime;


public record SchedullingResponse(
		Long id,
		LocalDateTime startTime,
		Integer duration
		) {

}
