package com.marcaai.adapter.dto.grouping.response.enterprise;

import com.marcaai.adapter.dto.response.address.AddressResponse;
import com.marcaai.adapter.dto.response.enterprise.EnterpriseResponse;


public record UpdateEnterpriseResponseGrouping(AddressResponse address, EnterpriseResponse enterprise) {

}
