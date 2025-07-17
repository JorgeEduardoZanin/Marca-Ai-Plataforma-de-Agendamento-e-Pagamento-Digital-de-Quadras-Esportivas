package com.marcaai.adapter.dto.response.schedulling;

import java.time.LocalDateTime;


public record SchedullingResponse(
		Long id,
		Long footbollCourtId,
		LocalDateTime startTime,
		Boolean reserved,
		LocalDateTime endTime,
		Integer duration
		) {

}
