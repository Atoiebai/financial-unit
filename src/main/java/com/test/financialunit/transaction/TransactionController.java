package com.test.financialunit.transaction;


import com.test.financialunit.transaction.dto.CreateTransactionRequest;
import com.test.financialunit.transaction.dto.TransactionBaseDto;
import com.test.financialunit.transaction.dto.TransactionsByUserResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
 class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionBaseDto> create(@RequestBody @Valid CreateTransactionRequest transactionRequest) {
        var resp = transactionService.create(transactionRequest);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    private ResponseEntity<TransactionsByUserResponse> getTransactions(
        @RequestParam(required = false, defaultValue = "2023-10-15T14:30:45")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime from ,
        @RequestParam(required = false, defaultValue = "2026-02-28T00:00:29")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime to
        ) {
        var resp = transactionService.findUserTransactions(from , to);
        return  ResponseEntity.ok(resp);
    }

}
