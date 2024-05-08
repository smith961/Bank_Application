package com.example.BankApplicationDevelopment.repository;

import com.example.BankApplicationDevelopment.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    Transactions findByTransactionId(String transactionId);


}
