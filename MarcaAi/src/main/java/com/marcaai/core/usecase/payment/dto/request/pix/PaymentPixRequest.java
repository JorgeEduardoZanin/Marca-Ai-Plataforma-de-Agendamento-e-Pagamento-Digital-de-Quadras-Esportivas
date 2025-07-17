package com.marcaai.core.usecase.payment.dto.request.pix;

import java.math.BigDecimal;


public record PaymentPixRequest(BigDecimal amount, int expiresIn, String name, String email, String cpf, String phoneNumber) {

}
