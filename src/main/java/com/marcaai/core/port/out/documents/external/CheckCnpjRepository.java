package com.marcaai.core.port.out.documents.external;

import com.marcaai.core.domain.group.CnpjGrouping;

public interface CheckCnpjRepository {

	CnpjGrouping checkCnpj(String cnpj);
	
}
