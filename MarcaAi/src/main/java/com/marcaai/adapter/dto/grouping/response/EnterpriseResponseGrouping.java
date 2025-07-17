package com.marcaai.adapter.dto.grouping.response;

import com.marcaai.adapter.dto.response.address.AddressResponse;
import com.marcaai.adapter.dto.response.companyowner.CompanyOwnerResponse;
import com.marcaai.adapter.dto.response.enterprise.EnterpriseResponse;

public record EnterpriseResponseGrouping(AddressResponse addres, EnterpriseResponse enterprise, CompanyOwnerResponse companyOwner) {

}
