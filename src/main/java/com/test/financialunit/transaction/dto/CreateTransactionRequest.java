package com.test.financialunit.transaction.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateTransactionRequest(
   @NotNull @Positive(message = "amount cant be negative") Long amount,
   @NotNull(message = "required field transaction type") TransactionType type
) {
}
