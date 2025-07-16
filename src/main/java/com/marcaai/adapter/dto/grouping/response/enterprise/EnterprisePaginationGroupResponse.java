package com.marcaai.adapter.dto.grouping.response.enterprise;

import java.util.List;

import com.marcaai.adapter.dto.response.enterprise.EnterpriseSummaryResponse;

public record EnterprisePaginationGroupResponse(List<EnterpriseSummaryResponse> enterpriseList, int pageSize, int page, Long totalElements, int totalPages){

}
