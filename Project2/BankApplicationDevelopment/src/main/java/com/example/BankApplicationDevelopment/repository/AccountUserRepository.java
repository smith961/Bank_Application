package com.example.BankApplicationDevelopment.repository;

import com.example.BankApplicationDevelopment.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountUserRepository extends JpaRepository<AccountUser, Integer> {
    AccountUser findByUsername(String username);
}
