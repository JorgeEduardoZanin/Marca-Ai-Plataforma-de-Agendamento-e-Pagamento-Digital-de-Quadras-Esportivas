package com.marcaai.adapter.dto.grouping.response.footballcourt;

import java.util.List;

import com.marcaai.adapter.dto.response.footballcourt.FootballCourtSummaryResponse;

public record FootballCourtPaginationGroupResponse(List<FootballCourtSummaryResponse> courtResponse, int pageSize, int page, Long totalElements, int totalPages) {

}
