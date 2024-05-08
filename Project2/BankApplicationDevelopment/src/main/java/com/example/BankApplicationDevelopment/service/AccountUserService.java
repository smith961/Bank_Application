package com.example.BankApplicationDevelopment.service;

import com.example.BankApplicationDevelopment.config.AccountConfiguration;
import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.LoginRequest;
import com.example.BankApplicationDevelopment.model.LoginResponse;
import com.example.BankApplicationDevelopment.model.Role;
import com.example.BankApplicationDevelopment.repository.AccountUserRepository;
import jakarta.mail.MessagingException;
import lombok.SneakyThrows;
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

    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountConfiguration accountConfiguration;

    @Autowired
    private BankAccountService bankAccountService;




    //getAll

    public ResponseEntity<List<AccountUser>> getAllAccountUser() {
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }

    //getById

    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id) {
        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<LoginResponse> authenticate(LoginRequest request) throws MessagingException {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (auth != null) {
            AccountUser accountUser = accountUserRepository.findByUsername(request.getUsername());


            String token = jwtService.createToken(accountUser);
            messageService.loginNotification(accountUser.getUsername(), "Dear" + accountUser.getFirstName() + "\n" + "There" +
                    "has been a successful login into your Banking Account. Please if you did not log in" +
                    "call us on the following number: 01-2245845, 08004455454\n"
                    + "Thank you for Banking with us.");
            return new ResponseEntity<>(LoginResponse.builder().username(accountUser).token(token).build(), HttpStatus.OK);
        }
        return null;


    }

    //getByUsername
    public ResponseEntity<AccountUser> getByUsername(@RequestParam String email) {
        return new ResponseEntity<>(accountUserRepository.findByUsername(email), HttpStatus.OK);

    }


    //POST


    public ResponseEntity<AccountUser> postAccountUser(@RequestBody AccountUser accountUser) throws MessagingException {
        passwordEncoder = accountConfiguration.passwordEncoder();
        accountUser.setPassword(passwordEncoder.encode(accountUser.getPassword()));
        accountUser.setRole(Role.USER);
        messageService.registrationNotification(accountUser.getUsername(), accountUser.getFirstName());
        AccountUser savedUser = accountUserRepository.save(accountUser);
        bankAccountService.createBankAccount(savedUser);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
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


}
