package com.example.BankApplicationDevelopment.controller;

import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.LoginRequest;
import com.example.BankApplicationDevelopment.model.LoginResponse;
import com.example.BankApplicationDevelopment.service.AccountUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountUser")
public class AccountUserController {

    @Autowired
    private AccountUserService accountUserService;

    @GetMapping("/allAccountUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AccountUser>> getAllAccountUser() {
        return accountUserService.getAllAccountUser();
    }

    @GetMapping("/accountUser/{id}")
    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id) {
        return accountUserService.getAccountUserById(id);
    }

    @GetMapping("/accountUser/{email}")
    public ResponseEntity<AccountUser> getByUsername(@PathVariable String email) {
        return accountUserService.getByUsername(email);

    }

    @PostMapping("/addAccountUser")
    public ResponseEntity<AccountUser> postAccountUser( @RequestBody AccountUser accountUser) {
        return accountUserService.postAccountUser(accountUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id, @Valid @RequestBody AccountUser replacementDetails) {
        return accountUserService.updateAccountUser(id, replacementDetails);


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest){
        return  accountUserService.authenticate(loginRequest);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AccountUser> deleteAccountUser(@PathVariable int id) {
        return accountUserService.deleteAccountUser(id);
    }


}
