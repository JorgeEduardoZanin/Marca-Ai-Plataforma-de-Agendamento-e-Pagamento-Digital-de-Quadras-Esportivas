package com.marcaai.adapter.dto.grouping.request;

import com.marcaai.adapter.dto.request.companyowner.createCompanyOwnerRequest;
import com.marcaai.adapter.dto.request.enterprise.CreateEnterpriseRequest;

public record CreateEnterpriseGrouping(CreateEnterpriseRequest enterprise, createCompanyOwnerRequest companyOwner) {

}
