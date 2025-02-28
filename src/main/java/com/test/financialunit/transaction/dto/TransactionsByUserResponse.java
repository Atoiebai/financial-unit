package com.test.financialunit.transaction.dto;


import java.util.List;

public record TransactionsByUserResponse(
    List<TransactionResponse> transactions
) {
}
