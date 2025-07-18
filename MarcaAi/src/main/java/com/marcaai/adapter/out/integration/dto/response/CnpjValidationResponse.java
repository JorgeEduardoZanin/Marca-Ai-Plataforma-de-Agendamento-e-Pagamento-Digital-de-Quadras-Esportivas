package com.marcaai.adapter.out.integration.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marcaai.core.domain.Address;
import com.marcaai.core.domain.document.Cnpj;
import com.marcaai.core.domain.group.CnpjGrouping;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CnpjValidationResponse(
			@JsonProperty("situacao")  String situacao,
		    @JsonProperty("cnpj")      String cnpj,
		    @JsonProperty("nome")      String nome,
		    @JsonProperty("fantasia")  String fantasia,
		    @JsonProperty("logradouro")String logradouro,
		    @JsonProperty("numero")    String numero,
		    @JsonProperty("cep")       String cep,
		    @JsonProperty("bairro")    String bairro,
		    @JsonProperty("municipio") String municipio,
		    @JsonProperty ("uf")       String uf	
		) {
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static CnpjGrouping cnpjResponseToCnpjDomain(CnpjValidationResponse cnpj) {
		
		var newCnpj =  new Cnpj(cnpj.situacao, cnpj.cnpj, cnpj.nome, cnpj.fantasia);
		var newAddress =  new Address(cnpj.uf, cnpj.logradouro, cnpj.numero, cnpj.municipio, cnpj.cep, cnpj.bairro, null);
		
		return new CnpjGrouping(newCnpj, newAddress);
		
	}

}
