package com.example.BankApplicationDevelopment.service;

import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.BankAccount;
import com.example.BankApplicationDevelopment.repository.BankAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRespository bankAccountRespository;

    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        return new ResponseEntity<>(bankAccountRespository.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<BankAccount> getById(int id) {
        return new ResponseEntity<>(bankAccountRespository.findById(id).get(), HttpStatus.OK);

    }

    public ResponseEntity<BankAccount> getByUser (AccountUser accountUser){
        return  new ResponseEntity<>(bankAccountRespository.findByUser(accountUser), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> getByAccountNUmber(String accountNumber){
        return new ResponseEntity<>(bankAccountRespository.findBankAccountByAccountNumber(accountNumber), HttpStatus.OK);

    }

    public ResponseEntity<BankAccount> updateAccount(BankAccount bankAccount){
        return new ResponseEntity<>(bankAccountRespository.save(bankAccount), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount> createBankAccount(AccountUser accountUser, double openingAmount){
        StringBuilder accountNumber = new StringBuilder();
        int count = 0;
        while(count < accountNumber.length()){
            int randomInt = new Random().nextInt(10);
            accountNumber.append(randomInt);
        }
        BankAccount account = new BankAccount(accountNumber.toString(), openingAmount, accountUser);
        return new ResponseEntity<>(bankAccountRespository.save(account), HttpStatus.CREATED);
    }

    public ResponseEntity<BankAccount> createBankAccount(AccountUser accountUser){
        StringBuilder accountNumber = new StringBuilder();
        int count = 0;
        while(count<accountNumber.length()){
            int randomInt = new Random().nextInt(10);
            accountNumber.append(randomInt);
        }
        BankAccount account = new BankAccount(accountNumber.toString(), 0.00, accountUser);
        return new ResponseEntity<>(bankAccountRespository.save(account), HttpStatus.CREATED);
    }
}
