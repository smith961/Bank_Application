package com.example.BankApplicationDevelopment.controller;

import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.BankAccount;
import com.example.BankApplicationDevelopment.model.OperationRequest;
import com.example.BankApplicationDevelopment.model.TransferRequest;
import com.example.BankApplicationDevelopment.service.BankAccountService;
import com.example.BankApplicationDevelopment.service.BankingOperationService;
import com.example.BankApplicationDevelopment.service.TransactionService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    @Autowired
    private BankingOperationService operationService;

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/deposit")
    public ResponseEntity<BankAccount> depositFund(@RequestBody OperationRequest request) throws MessagingException{
        return new ResponseEntity<>(operationService.depositFund(request.getAccountNumber(), request.getAmount(), transactionService.generateTxnId()).getBody(), HttpStatus.OK);
}

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccount> withdrawFund(@RequestBody OperationRequest request) throws  MessagingException{
        return new ResponseEntity<>(operationService.withdrawFund(request.getAccountNumber(), request.getAmount(), transactionService.generateTxnId()).getBody(), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity<String> transferFunds(TransferRequest request ) throws MessagingException{
        try{
            operationService.transferFunds(request.getAccountFrom(), request.getAccountTo(), request.getAmount());
            return new ResponseEntity<>("Transaction Successful", HttpStatus.OK);
        } catch (TransactionException transactionException){
            System.out.println(transactionException.getMessage());
        }
        return new ResponseEntity<>("Transaction failed", HttpStatus.NOT_ACCEPTABLE);
    }

}
