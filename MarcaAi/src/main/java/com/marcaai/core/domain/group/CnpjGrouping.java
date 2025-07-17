package com.marcaai.core.domain.group;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.document.Cnpj;

public record CnpjGrouping(Cnpj cnpj, Address address) {

}
