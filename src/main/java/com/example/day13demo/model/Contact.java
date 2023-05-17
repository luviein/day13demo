package com.example.day13demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Contact {
    @NotNull(message="Name cannot be empty")
    @Size(min = 3, max = 15, message ="Name should be between 3-15 characters")
    private String name;

    @NotEmpty(message="Email cannot be empty")
    @Email(message="Invalid Email")
    private String email;

    @Size(min = 8, message ="Invalid phone number")
    private String phoneNumber;
    
    
    
    public Contact() {
    }

    
    public Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }

    



}
