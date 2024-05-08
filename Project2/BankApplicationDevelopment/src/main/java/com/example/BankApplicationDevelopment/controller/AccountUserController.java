package com.example.BankApplicationDevelopment.controller;

import com.example.BankApplicationDevelopment.model.AccountResource;
import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.model.LoginRequest;
import com.example.BankApplicationDevelopment.model.LoginResponse;
import com.example.BankApplicationDevelopment.service.AccountUserService;
import com.example.BankApplicationDevelopment.service.MessageService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class AccountUserController {

   @Autowired
   private AccountUserService accountUserService;

   @Autowired
   private MessageService messageService;

   @GetMapping("/getResetCode")
   public void getResetCode(@RequestParam String username) throws  MessagingException{
       AccountUser user = accountUserService.getByUsername(username).getBody();
       StringBuilder randomCode = new StringBuilder();
       int count = 1;
       while (count <= 6){
           String x = String.valueOf(new Random().nextInt(10));
           randomCode.append(x);
           count ++;
       }
       messageService.sendResetCode(user.getUsername(), randomCode.toString());
   }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AccountUser>> getAllAccountUser() {
        return accountUserService.getAllAccountUser();
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id) {
        return accountUserService.getAccountUserById(id);
    }

    @GetMapping("/name")
    public ResponseEntity<AccountUser> getByUsername(@RequestParam String username) {
        return accountUserService.getByUsername(username);

    }

    @PostMapping("/register")
    public ResponseEntity<AccountUser> postAccountUser(@RequestBody AccountUser accountUser) throws MessagingException {
        return accountUserService.postAccountUser(accountUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) throws MessagingException {
        return accountUserService.authenticate(loginRequest);
    }

    @PutMapping("/all/{id}")
    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id,  @RequestBody AccountUser replacementDetails) {
        return accountUserService.updateAccountUser(id, replacementDetails);


    }


    @DeleteMapping("/all/{id}")
    public ResponseEntity<AccountUser> deleteAccountUser(@PathVariable int id) {
        return accountUserService.deleteAccountUser(id);
    }



    @GetMapping("/resource/{id}")
    public ResponseEntity<AccountResource> getAccountResource(@PathVariable int id) {
        AccountResource accountResource = new AccountResource();
        AccountUser accountUserToCreate = accountUserService.getAccountUserById(id).getBody();
        accountResource.setAccountUser(accountUserToCreate);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAccountUserById(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).updateAccountUser(id, accountUserToCreate)).withRel("updateUser");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).deleteAccountUser(id)).withRel("deleteUser");

        Link getAll = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAllAccountUser()).withRel("getAll");

        accountResource.add(selfLink,deleteLink,updateLink,getAll);

        return new ResponseEntity<>(accountResource, HttpStatus.OK);


    }


}
