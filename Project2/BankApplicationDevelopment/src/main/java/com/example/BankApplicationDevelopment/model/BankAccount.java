package com.example.BankApplicationDevelopment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
@Entity(name = "accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "[0-9]{10}")
    private String accountNumber;

    private double accountBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  AccountUser user;

    public BankAccount(){}

    public BankAccount(String accountNumber, double accountBalance, AccountUser user) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public AccountUser getUser() {
        return user;
    }

    public void setUser(AccountUser user) {
        this.user = user;
    }
}
