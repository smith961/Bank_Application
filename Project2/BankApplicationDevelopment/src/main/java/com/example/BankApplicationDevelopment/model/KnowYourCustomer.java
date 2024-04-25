package com.example.BankApplicationDevelopment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kyc")
public class KnowYourCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private String bankVerificationNumber;

    private String nin;

    private String localGovtOfResidence;
    private String stateOfResidence;
    private String dateOfBirth;
    private String nextOfKin;

    public KnowYourCustomer(){};

    @OneToOne
    @JoinColumn(name = "user_id")
    private AccountUser user;

    public KnowYourCustomer(String address, String bankVerificationNumber, String nin, String localGovtOfResidence, String stateOfResidence, String dateOfBirth, String nextOfKin, AccountUser user) {
        this.address = address;
        this.bankVerificationNumber = bankVerificationNumber;
        this.nin = nin;
        this.localGovtOfResidence = localGovtOfResidence;
        this.stateOfResidence = stateOfResidence;
        this.dateOfBirth = dateOfBirth;
        this.nextOfKin = nextOfKin;
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankVerificationNumber() {
        return bankVerificationNumber;
    }

    public void setBankVerificationNumber(String bankVerificationNumber) {
        this.bankVerificationNumber = bankVerificationNumber;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getLocalGovtOfResidence() {
        return localGovtOfResidence;
    }

    public void setLocalGovtOfResidence(String localGovtOfResidence) {
        this.localGovtOfResidence = localGovtOfResidence;
    }

    public String getStateOfResidence() {
        return stateOfResidence;
    }

    public void setStateOfResidence(String stateOfResidence) {
        this.stateOfResidence = stateOfResidence;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public AccountUser getUser() {
        return user;
    }

    public void setUser(AccountUser user) {
        this.user = user;
    }
}
