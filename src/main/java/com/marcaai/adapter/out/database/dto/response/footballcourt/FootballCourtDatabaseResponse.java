package com.marcaai.adapter.out.database.dto.response.footballcourt;

import java.math.BigDecimal;

import com.marcaai.adapter.out.database.entity.EnterpriseEntity;

public record FootballCourtDatabaseResponse(Long id, String name, EnterpriseEntity enterprise, BigDecimal value) {

}
