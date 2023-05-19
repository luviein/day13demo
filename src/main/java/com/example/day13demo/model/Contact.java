package com.example.day13demo.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

//validates the variables
public class Contact {
    @NotNull(message = "Name cannot be empty")
    @Size(min = 3, max = 15, message = "Name should be between 3-15 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid Email")
    private String email;

    @Size(min = 7, message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth should be from the past")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateOfBirth;

    @Min(value = 10, message = "Must be at least 10 years")
    @Max(value = 100, message = "Cannot be older than 100 years")
    private int age;

    private String id;
    //every time contact object is called, it generates ID via method
    public Contact() {
        this.id = generateID();
    }

    private String generateID() {
        Random rand = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < 8) {
            sb.append(Integer.toHexString(rand.nextInt()));

        }

        return sb.toString().substring(0, 8);
    }

    public Contact(String id,
            String name,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        // calculate age
        int calculatedAge = 0;
        if (dateOfBirth != null) {
            calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
        this.age = calculatedAge;

        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
