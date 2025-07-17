package com.marcaai.core.usecase.payment.dto.response.pix;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentPixResponse(BigDecimal amount, String status, String pixCode, String pixCodeBase64, LocalDateTime expiresAt, LocalDateTime createAt) {

}
