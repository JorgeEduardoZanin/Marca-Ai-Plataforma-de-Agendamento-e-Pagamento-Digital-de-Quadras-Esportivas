package com.marcaai.core.domain.document;

import com.marcaai.core.domain.Enterprise;
import com.marcaai.core.domain.enums.DocumentSituation;

public class Cnpj {

	String situation;
	String cnpj;
	String corporate_reason;
	String fantasy_name;
	
	public Cnpj() {
	}

	public Cnpj(String situation, String cnpj, String corporate_reason, String fantasy_name) {
		this.situation = situation;
		this.cnpj = cnpj;
		this.corporate_reason = corporate_reason;
		this.fantasy_name = fantasy_name;
	}

	

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCorporate_reason() {
		return corporate_reason;
	}

	public void setCorporate_reason(String corporate_reason) {
		this.corporate_reason = corporate_reason;
	}

	public String getFantasy_name() {
		return fantasy_name;
	}

	public void setFantasy_name(String fantasy_name) {
		this.fantasy_name = fantasy_name;
	}
	
	public boolean hasSameCorporateReason(Enterprise enterprise) {
		
		if(!corporate_reason.equals(enterprise.getCorporateReason())) {
			return false;
		}
		
		return true;
		
	}
	
	public boolean isActive() {
		return DocumentSituation.ATIVA.name().equals(this.situation);      
    }

	@Override
	public String toString() {
		return "Cnpj [situation=" + situation + ", cnpj=" + cnpj + ", corporate_reason=" + corporate_reason
				+ ", fantasy_name=" + fantasy_name + "]";
	}
	
	
	
}
