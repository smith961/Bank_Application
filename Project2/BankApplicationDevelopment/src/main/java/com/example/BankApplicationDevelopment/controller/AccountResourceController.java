package com.example.BankApplicationDevelopment.controller;

import com.example.BankApplicationDevelopment.model.AccountResource;
import com.example.BankApplicationDevelopment.model.AccountUser;
import com.example.BankApplicationDevelopment.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class AccountResourceController {
    @Autowired
    private  AccountUserService accountUserService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResource> getAccountResource(@PathVariable int id) {
        AccountUser accountUserToCreate = accountUserService.getAccountUserById(id).getBody();
        AccountResource accountResource = new AccountResource();
        accountResource.setAccountUser(accountUserToCreate);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAccountUserById(id)).withSelfRel();
        Link delete = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).deleteAccountUser(id)).withRel("delete");
        Link update = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).updateAccountUser(id, accountUserToCreate)).withRel("update");
        Link allAccountUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAllAccountUser()).withRel("allAccountUser");

        accountResource.add(selfLink,delete,update,allAccountUser);

        return new ResponseEntity<>(accountResource, HttpStatus.OK);


    }
}
