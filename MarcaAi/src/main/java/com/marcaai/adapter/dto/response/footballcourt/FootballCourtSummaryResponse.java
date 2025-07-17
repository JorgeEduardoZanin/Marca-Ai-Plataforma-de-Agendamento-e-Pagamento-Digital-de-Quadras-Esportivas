package com.marcaai.adapter.dto.response.footballcourt;

import java.math.BigDecimal;
import java.util.UUID;

public record FootballCourtSummaryResponse(String name, BigDecimal value, UUID enterpriseId, Long id) {

}
