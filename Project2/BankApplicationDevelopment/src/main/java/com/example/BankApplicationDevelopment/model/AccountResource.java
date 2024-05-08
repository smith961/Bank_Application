package com.example.BankApplicationDevelopment.model;

import org.springframework.hateoas.RepresentationModel;

public class AccountResource extends RepresentationModel<AccountResource> {
    private AccountUser accountUser;

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }


}
