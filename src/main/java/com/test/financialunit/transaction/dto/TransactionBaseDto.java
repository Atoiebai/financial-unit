package com.test.financialunit.transaction.dto;


import com.test.financialunit.transaction.TransactionStatus;
import java.util.UUID;

public record TransactionBaseDto(
     Long id,
     UUID userId,
     Long amount,
     TransactionStatus status) {

}
