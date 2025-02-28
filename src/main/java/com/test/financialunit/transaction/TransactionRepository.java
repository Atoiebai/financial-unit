package com.test.financialunit.transaction;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface TransactionRepository extends JpaRepository<Transaction , Long> {

    @Query(value = """
                select * from transactions
                where user_id = :userId and created_at between :from and :to
                """, nativeQuery = true)
    List<Transaction> findBy(
        @Param("userId") UUID userId,
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to
    );

}
