package com.example.BankApplicationDevelopment.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    private String username;
    private String password;
}
