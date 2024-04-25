package com.example.BankApplicationDevelopment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*
Bean to Creaate
userDetailsService
passWordEncoder
authenticationManager;
authenticationProvider;
*/

@Entity
@Table(name = "account_users")
public class AccountUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull(message = "firstname is required")
    @NotBlank
    @Length(min = 3)
    private String firstName;

    @NotNull(message = "lastname is required")
    @NotBlank
    @Length(min = 3)
    private String lastName;

    private String middleName;

    @NotNull(message = "email is required")
    @Column(unique = true)
    @NotBlank
    @Email
    private String username;

    @NotNull(message = "password is required")
    @NotBlank
    @Length(min = 6)
    private String password;

    @NotNull(message = "phone number is required")
    @Pattern(regexp = "[0-9]{11}")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public AccountUser(){}

    public AccountUser(String firstName, String lastName, String middleName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.username = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

//    public Role getRole(){
//        return  this.role;
//    }
//
//    public void setRole(){
//        return this.role;
//    }

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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name())); //List<SimpleGrantedAuthority>

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



    @Override
    public String toString() {
        return "AccountUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
