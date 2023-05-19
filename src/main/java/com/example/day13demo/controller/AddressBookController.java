package com.example.day13demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.day13demo.model.Contact;
import com.example.day13demo.service.Contacts;
import com.example.day13demo.utility.Utility;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/")
public class AddressBookController {
    @Autowired
    Utility utility;

    @Autowired
    Contacts service;

    @Value("${data.dir}") //injects value of data.dir in application properties into string dataDir
    private String dataDir;

    // request method to load landing page
    @GetMapping
    public String showAddressBook(Model model) {
        //initialises new contact object when addressbook is loaded. model key value pair: "contact" : empty Contact object
        model.addAttribute("contact", new Contact());
        return "addressBook";
    }

    // to save contact info (when user submits the form)
    @PostMapping(consumes = "application/x-www-form-urlencoded", path = "/contact")
    //@Valid checks the validation in Contact class, binding result retrieves error information, model to add success message to be displayed in the HTML
    public String saveAddressBook(@Valid Contact contact, BindingResult bindingResult, Model model) { 
        //dont need to instantiate contact object as @Valid annotation ensures that contact object is auto instantiated and populated with values from form data when 
        //method is invoked

        if (bindingResult.hasErrors()) {

            System.out.println(bindingResult.toString());
            return "addressBook";
        }
        // semantic validaton
        // custom data validation
        // if(!utility.isUniqueEmail(contact.getEmail())){
        // ObjectError err = new ObjectError("globalError", "%s is not
        // available".formatted(contact.getEmail()));
        // bindingResult.addError(err);
        // }

        //use save method to save the validated contact info, success message, data directory path
        //contact refers to the auto instantiated contact @ saveAddressBook
        service.save(contact, model, dataDir);
        //adds success message to model to be used in html = "successmessage"
        model.addAttribute("successMessage",
                "Contact saved successfully with status code: " + HttpStatus.CREATED + ".");

        return "showContact";
  
    }

    @GetMapping("/contact/{contactID}")
        public String getContactById(Model model, @PathVariable String contactID) {
            Contact contact = new Contact();
            contact = service.getContactById(contactID, dataDir); //calling method in service which returns the ctc object
            if(contact == null) {
                model.addAttribute("errorMessage", "Contact not found");
                return "error";
            }
            model.addAttribute("contact", contact); //appends ctc details in show contact
            return "showContact";

    }

    @GetMapping(path ="/list")
    public String getAllContacts(Model model) {
        service.getAllContactInURI(model, dataDir);
        return "contacts";
    }
}
