package com.example.day13demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.day13demo.model.Contact;

import jakarta.validation.Valid;

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
    @PostMapping(consumes = "application/x-www-form-urlencoded", path = "/contact")
    public String saveAddressBook(@Valid Contact contact, BindingResult bindingResult, Model model){
     
                
        
        System.out.println("Name: " + contact.getName());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Phone Number: " + contact.getPhoneNumber());

        if(bindingResult.hasErrors()){
            //System.out.println("Error count ---> " + result.getErrorCount());
            System.out.println(bindingResult.toString());
            return "addressBook";
        }

           // String name = form.getFirst("name");
        // String email = form.getFirst("email");
        // String phone = form.getFirst("phoneNumber");

        // System.out.println("name: " + name); 
        // System.out.println("email: " + email);
        // System.out.println("phone: " + phone);
        return "addressBook";
    }


}
