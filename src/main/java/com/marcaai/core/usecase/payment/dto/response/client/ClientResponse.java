package com.marcaai.core.usecase.payment.dto.response.client;

public record ClientResponse(String clientPaymentId, String name, String cellphone, String email, String cpf_cnpj) {

}
