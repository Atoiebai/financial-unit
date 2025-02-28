package com.test.financialunit.transaction.dto;


import com.test.financialunit.transaction.TransactionStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
    Long id,
    UUID userId,
    Long amount,
    TransactionStatus status,
    LocalDateTime createdAt
) {
}
