package com.example.BankApplicationDevelopment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

@Entity(name = "users_table")
public class AccountUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;


    @NotBlank
    @Length(min = 3)
    private String firstName;


    private String middleName;

    @NotBlank
    @Length(min = 3)
    private String lastName;

    @NotBlank
    @Column(unique = true)
    @Email
    private String username;


    @NotBlank
    @Length(min = 6)
    private String password;


    @Pattern(regexp = "[0-9]{11}")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public AccountUser() {}

    public AccountUser(String firstName, String middleName, String lastName, String username, String password, String phoneNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name())); //List<SimpleGrantedAuthority>

    }

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId(){
        return id;
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
