package com.marcaai.core.domain.group;

import java.util.List;

import com.marcaai.core.domain.FootballCourt;

public record FootballCourtPaginationGrouping(List<FootballCourt> courtList, Long totalElements, int totalPages) {

}
