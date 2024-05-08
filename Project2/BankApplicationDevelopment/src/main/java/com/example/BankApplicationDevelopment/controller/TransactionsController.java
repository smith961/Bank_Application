package com.example.BankApplicationDevelopment.controller;

import com.example.BankApplicationDevelopment.model.Transactions;
import com.example.BankApplicationDevelopment.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        return transactionService.getAllTransactions();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getById(@PathVariable long id) {
        return transactionService.getById(id);
    }

    @GetMapping("/transId")
    public ResponseEntity<Transactions> getByTransactionId(@RequestParam String transactionId) {
        return transactionService.getByTransactionId(transactionId);
    }

    @PostMapping("")
    public ResponseEntity<Transactions> postNewTransaction(@RequestBody @Valid Transactions transactions) {
        return transactionService.postNewTransaction(transactions);
    }
}
