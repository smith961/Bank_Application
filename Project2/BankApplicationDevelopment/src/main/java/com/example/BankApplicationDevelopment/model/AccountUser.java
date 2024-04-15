package com.example.BankApplicationDevelopment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "account_users")
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "firstname is required")
    private String firstName;
    @NotNull(message = "lastname is required")
    private String lastName;
    private String middleName;
    @NotNull(message = "email is required")
    private String username;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "phone number is required")
    private String phoneNumber;

    public AccountUser(){}

    public AccountUser(String firstName, String lastName, String middleName, String username, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
