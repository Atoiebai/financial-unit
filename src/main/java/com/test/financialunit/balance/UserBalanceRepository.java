package com.test.financialunit.balance;


import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

 interface UserBalanceRepository extends JpaRepository<UserBalance , Long> {

     Optional<UserBalance> findByUserId(UUID userId);

}
