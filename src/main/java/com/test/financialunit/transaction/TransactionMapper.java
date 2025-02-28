package com.test.financialunit.transaction;


import com.test.financialunit.transaction.dto.CreateTransactionRequest;
import com.test.financialunit.transaction.dto.TransactionBaseDto;
import com.test.financialunit.transaction.dto.TransactionResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface TransactionMapper {


    Transaction toEntity(CreateTransactionRequest createTransactionRequest);

    TransactionBaseDto transactionBaseResponse(Transaction transaction);


    List<TransactionResponse> transactionsResponse(List<Transaction> transactions);
}
