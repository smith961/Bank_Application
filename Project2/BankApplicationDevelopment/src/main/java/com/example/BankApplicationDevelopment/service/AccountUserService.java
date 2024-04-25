package com.example.BankApplicationDevelopment.service;

import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.LoginRequest;
import com.example.BankApplicationDevelopment.model.LoginResponse;
import com.example.BankApplicationDevelopment.repository.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountUserService {
    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    //POST

    public ResponseEntity<AccountUser> postAccountUser(@RequestBody AccountUser accountUser) {
        accountUser.setPassword(passwordEncoder.encode(accountUser.getPassword()));
        return new ResponseEntity<>(accountUserRepository.save(accountUser), HttpStatus.CREATED);
    }

    //getAll

    public ResponseEntity<List<AccountUser>> getAllAccountUser() {
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }

    //getById

    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id) {
        try {
            return new ResponseEntity<AccountUser>(accountUserRepository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //getByUsername
    public ResponseEntity<AccountUser> getByUsername(@RequestParam String email) {
        try {
            return new ResponseEntity<AccountUser>(accountUserRepository.findByUsername(email), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    //PUT

    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id, AccountUser replacementDetails) {
        AccountUser user = accountUserRepository.findById(id).get();
        user.setFirstName(replacementDetails.getFirstName());
        user.setLastName(replacementDetails.getLastName());
        user.setMiddleName(replacementDetails.getMiddleName());
        user.setUsername(replacementDetails.getUsername());
        user.setPassword(replacementDetails.getPassword());
        user.setPhoneNumber(replacementDetails.getPhoneNumber());

        return new ResponseEntity<>(accountUserRepository.save(user), HttpStatus.OK);


    }

    //DELETE

    public ResponseEntity<AccountUser> deleteAccountUser(int id) {
        AccountUser user = accountUserRepository.findById(id).get();
        accountUserRepository.deleteById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<LoginResponse> authenticate(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (auth != null) {
            AccountUser accountUser = accountUserRepository.findByUsername(request.getUsername());

            String token = jwtService.createToken(accountUser);
            return new ResponseEntity<>(LoginResponse.builder().username(accountUser).token(token).build(), HttpStatus.OK);
        }
        return null;


    }


}
