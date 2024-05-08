package com.example.BankApplicationDevelopment.exception;

public class TransactionException extends RuntimeException{
    private String message;

    public TransactionException(String message){
        super(message);
    }
}
