package com.example.BankApplicationDevelopment.model;

import com.example.BankApplicationDevelopment.service.AccountUserService;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private AccountUser username;
    private String token;
}
