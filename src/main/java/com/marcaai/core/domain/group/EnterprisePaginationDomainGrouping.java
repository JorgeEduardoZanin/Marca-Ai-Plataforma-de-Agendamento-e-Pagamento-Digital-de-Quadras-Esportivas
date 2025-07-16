package com.marcaai.core.domain.group;

import java.io.Serializable;
import java.util.List;

import com.marcaai.core.domain.Enterprise;

public record EnterprisePaginationDomainGrouping(List<Enterprise> enterpriseList, Long totalElements, int totalPages) implements Serializable {

}
