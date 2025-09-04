package com.marcaai.adapter.dto.request.order;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record OrderRequest(
		@NotEmpty List<Long> schedulingsId,
		String description) {

}
