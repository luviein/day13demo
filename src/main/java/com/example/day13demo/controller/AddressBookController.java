package com.example.day13demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.day13demo.model.Contact;

@Controller
@RequestMapping(path = "/")
public class AddressBookController {
    
    //request method to load landing page
    @GetMapping
    public String showAddressBook(Model model){
        model.addAttribute("contact", new Contact());
        return "addressBook";
    }

    //to save contact info
    @PostMapping("/contact")
    public String saveAddressBook(Contact contact, Model model){
        System.out.println("Name: " + contact.getName());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Phone Number: " + contact.getPhoneNumber());
        return "addressBook";
    }


}
