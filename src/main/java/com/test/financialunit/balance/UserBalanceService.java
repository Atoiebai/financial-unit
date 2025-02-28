package com.test.financialunit.balance;


import com.test.financialunit.transaction.TransactionStatus;
import com.test.financialunit.transaction.dto.CreateTransactionRequest;
import com.test.financialunit.transaction.dto.TransactionType;
import com.test.financialunit.user.UserService;
import com.test.financialunit.util.SecurityUtils;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserBalanceService {

    private final UserBalanceRepository userBalanceRepository;
    private final UserService userService;

    Map<TransactionType , BiFunction<UserBalance, CreateTransactionRequest, TransactionStatus>> handlers =  Map.of(
        TransactionType.DEBIT , this::debitHandler,
        TransactionType.CREDIT, this::creditHandler
    );

    private TransactionStatus creditHandler(UserBalance userBalance, CreateTransactionRequest request) {
        var currentAmount = userBalance.getAmount();
        var totalAmount = currentAmount + request.amount();
        userBalance.setAmount(totalAmount);
        log.debug("Increased balance for user: {} , old balance: {} , new balance: {}" , userBalance.getUserId() , currentAmount , totalAmount);
        userBalanceRepository.save(userBalance);
        return TransactionStatus.SUCCESS;
    }

    private TransactionStatus debitHandler(UserBalance userBalance, CreateTransactionRequest request) {
        var currentAmount = userBalance.getAmount();
        if(currentAmount < request.amount()) return TransactionStatus.FAILED;
        var totalAmount = currentAmount - request.amount();
        userBalance.setAmount(totalAmount);
        log.debug("Decreased balance for user: {} , old balance: {} , new balance: {}" , userBalance.getUserId() , currentAmount , totalAmount);
        userBalanceRepository.save(userBalance);
        return TransactionStatus.SUCCESS;
    }


    public void createUserBalance(UUID userId) {
        userBalanceRepository.findByUserId(userId)
            .orElseGet(() -> userBalanceRepository.save(createNewBalance(userId)));
    }


    private UserBalance createNewBalance(UUID userId) {
        var balance = new UserBalance();
        balance.setUserId(userId);
        balance.setAmount(0L);
        return balance;
    }

    public TransactionStatus applyTransaction(CreateTransactionRequest request) {
        var authInfo = SecurityUtils.getCurrentAuthenticated();
        var user = userService.findBy(authInfo.getUsername());
        return userBalanceRepository.findByUserId(user.getId())
            .map(balance -> handlers.get(request.type()).apply(balance, request))
            .orElseGet(() -> {
                log.warn("No handler found for given transaction type: {}" , request.type());
                return TransactionStatus.FAILED;
            });


    }
}
