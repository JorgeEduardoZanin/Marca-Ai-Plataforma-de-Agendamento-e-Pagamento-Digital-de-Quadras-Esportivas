package com.marcaai.core.domain.group;

import java.io.Serializable;

import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.CompanyOwner;
import com.marcaai.core.domain.Enterprise;

public record EnterpriseDomainGrouping(Enterprise enterprise, Address address, CompanyOwner companyOwner) implements Serializable {

}
