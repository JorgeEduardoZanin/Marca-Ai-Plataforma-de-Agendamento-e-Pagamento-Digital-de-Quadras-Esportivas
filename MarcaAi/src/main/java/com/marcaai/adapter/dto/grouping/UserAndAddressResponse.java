package com.marcaai.adapter.dto.grouping;

import com.marcaai.adapter.dto.response.address.AddressResponse;
import com.marcaai.adapter.dto.response.usercrud.UserCrudResponse;

public record UserAndAddressResponse(UserCrudResponse userCrudResponse, AddressResponse addressResponse) {

}
