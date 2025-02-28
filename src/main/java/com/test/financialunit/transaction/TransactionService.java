package com.test.financialunit.transaction;


import com.test.financialunit.balance.UserBalanceService;
import com.test.financialunit.transaction.dto.CreateTransactionRequest;
import com.test.financialunit.transaction.dto.TransactionBaseDto;
import com.test.financialunit.transaction.dto.TransactionResponse;
import com.test.financialunit.transaction.dto.TransactionsByUserResponse;
import com.test.financialunit.user.UserService;
import com.test.financialunit.util.SecurityUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final UserBalanceService userBalanceService;
    private final UserService userService;


    public TransactionBaseDto create(CreateTransactionRequest request) {
       var authenticated = SecurityUtils.getCurrentAuthenticated();
       var user = userService.findBy(authenticated.getUsername());
       var transaction = transactionMapper.toEntity(request);
       var status = userBalanceService.applyTransaction(request);
       transaction.setUserId(user.getId());
       transaction.setStatus(status);
       transactionRepository.save(transaction);
      return transactionMapper.transactionBaseResponse(transaction);
    }

    public TransactionsByUserResponse findUserTransactions(LocalDateTime from , LocalDateTime to) {
        var authInfo = SecurityUtils.getCurrentAuthenticated();
        var user = userService.findBy(authInfo.getUsername());
        var result = getTransactions(user.getId(), from , to);
        return new TransactionsByUserResponse(result);
    }

    public List<TransactionResponse> getTransactions(UUID userId, LocalDateTime from , LocalDateTime to) {
        var transactions =  transactionRepository
            .findBy(userId , from ,to);

        return transactionMapper.transactionsResponse(transactions);

    }
}

