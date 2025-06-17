package com.marcaai.adapter.dto.grouping.request;

import com.marcaai.adapter.dto.request.address.AddressRequest;
import com.marcaai.adapter.dto.request.companyowner.CompanyOwnerRequest;
import com.marcaai.adapter.dto.request.enterprise.EnterpriseRequest;

public record CreateEnterpriseGrouping(EnterpriseRequest enterprise, CompanyOwnerRequest companyOwner, AddressRequest address) {

}
