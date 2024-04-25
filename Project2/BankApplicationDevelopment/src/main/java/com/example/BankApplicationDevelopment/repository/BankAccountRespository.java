package com.example.BankApplicationDevelopment.repository;

import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRespository extends JpaRepository<BankAccount, Integer> {
    BankAccount findByUser(AccountUser user);
}
