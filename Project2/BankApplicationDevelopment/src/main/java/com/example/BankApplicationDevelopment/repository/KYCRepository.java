package com.example.BankApplicationDevelopment.repository;

import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.KnowYourCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository extends JpaRepository<KnowYourCustomer, Integer> {
    KnowYourCustomer getByUser(AccountUser user);
}
