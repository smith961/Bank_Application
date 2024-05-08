package com.example.BankApplicationDevelopment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Entity(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Length(min = 10)
    private String accountNumber;


    private Date transactionDate;

    private String transactionTime;


    private double amount;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

}
