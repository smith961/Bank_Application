package com.example.BankApplicationDevelopment.service;

import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.repository.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountUserService {
    @Autowired
    private AccountUserRepository accountUserRepository;

    //POST

    public ResponseEntity<AccountUser>postAccountUser(AccountUser accountUser){
        return new ResponseEntity<>(accountUserRepository.save(accountUser), HttpStatus.CREATED);
    }

    //getAll

    public ResponseEntity<List<AccountUser>>getAllAccountUser(){
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }

    //getById

    public ResponseEntity<AccountUser>getAccountUserById(int id){
        try{
            return  new ResponseEntity<AccountUser>(accountUserRepository.findById(id).get(), HttpStatus.OK);
        }catch (NoSuchElementException exception){
            System.out.println(exception.getMessage());
            return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
//getByUsername
    public ResponseEntity<AccountUser>getByUsername(String email){
        try{
            return new ResponseEntity<AccountUser>(accountUserRepository.findByUsername(email).get(),HttpStatus.OK);
        }catch (NoSuchElementException exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    //PUT

    public ResponseEntity<AccountUser>updateAccountUser(int id, AccountUser replacementDetails){
        AccountUser user = accountUserRepository.findById(id).get();
        user.setFirstName(replacementDetails.getFirstName());
        user.setLastName(replacementDetails.getLastName());
        user.setMiddleName(replacementDetails.getMiddleName());
        user.setEmail(replacementDetails.getEmail());
        user.setPassword(replacementDetails.getPassword());
        user.setPhoneNumber(replacementDetails.getPhoneNumber());

        return new ResponseEntity<>(accountUserRepository.save(user), HttpStatus.OK);



    }

    //DELETE

    public ResponseEntity<AccountUser>deleteAccountUser(int id){
        AccountUser user = accountUserRepository.findById(id).get();
        accountUserRepository.deleteById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
