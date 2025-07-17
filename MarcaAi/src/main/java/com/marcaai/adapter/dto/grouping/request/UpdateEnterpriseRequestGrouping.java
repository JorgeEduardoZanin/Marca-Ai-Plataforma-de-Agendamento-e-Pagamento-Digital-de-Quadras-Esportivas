package com.marcaai.adapter.dto.grouping.request;

import com.marcaai.adapter.dto.request.address.AddressRequest;
import com.marcaai.adapter.dto.request.enterprise.UpdateEnterpriseRequest;

public record UpdateEnterpriseRequestGrouping(AddressRequest address, UpdateEnterpriseRequest enterprise) {

}
