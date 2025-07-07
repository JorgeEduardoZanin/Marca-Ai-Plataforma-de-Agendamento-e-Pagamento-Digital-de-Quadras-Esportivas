package com.marcaai.adapter.out.database.dto.response;

import java.math.BigDecimal;

import com.marcaai.adapter.out.database.entity.EnterpriseEntity;

public record FootballCourtRepositoryDatabaseResponse(Long id, String name, EnterpriseEntity enterprise, BigDecimal value) {

}
