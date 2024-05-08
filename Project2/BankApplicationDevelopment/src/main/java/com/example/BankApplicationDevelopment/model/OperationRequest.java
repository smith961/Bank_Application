package com.example.BankApplicationDevelopment.model;

import lombok.Data;

@Data
public class OperationRequest {
    private String accountNumber;
    private double amount;
}
