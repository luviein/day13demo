package com.example.day13demo.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.FacesWebRequest;

import com.example.day13demo.model.Contact;

import jakarta.validation.Valid;

@Service // can also use service
public class Contacts {

    // using contact instantiated in the addressbook when it was at @Valid
    public void save(Contact contact, Model model, String dataDir) { // gets contact object
        String fileName = contact.getId();
        PrintWriter printWriter = null;

        try {
            FileWriter fw = new FileWriter(dataDir + "/" + fileName + ".txt");
            printWriter = new PrintWriter(fw);
            printWriter.println(contact.getName());
            printWriter.println(contact.getEmail());
            printWriter.println(contact.getPhoneNumber());
            printWriter.println(contact.getDateOfBirth());
            model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), contact.getEmail(),
                    contact.getPhoneNumber(), contact.getDateOfBirth()));
            // creates new contact object based on properties of existing contact
            // object(@Valid). represents updated state of contact
            printWriter.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void getAllContactInURI(Model model, String dataDir) {
        Set<String> dataFiles = listFiles(dataDir);
        Set<String> modifiedFiles = new HashSet<String>();
        for (String file : dataFiles) {
            // removing .txt from each file
            String modifiedFile = file.replace(".txt", "");
            if (!modifiedFile.isEmpty()) {
                modifiedFiles.add(modifiedFile);
            }
        }
        // value = create array of strings obtained from modified files with size of
        // data Files. which means it can hold all elements from the set
        // puts in string array so html can iterate
        //String[] contactsArray = modifiedFiles.toArray(new String[dataFiles.size()]);
        //String contactsString = Arrays.toString(contactsArray);
        model.addAttribute("contacts", modifiedFiles);
        System.out.println(modifiedFiles);
        
    }

    private Set<String> listFiles(String dataDir) {
        return Stream.of(new File(dataDir).listFiles()).filter(file -> !file.isDirectory()).map(File::getName)
                .collect(Collectors.toSet());

    }

    public Contact getContactById(String contactId, String dataDir) { // ID is already set thats why pass the existing ID to get the contact
        Contact ctc = new Contact();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.nio.file.Path filePath = new File(dataDir + "/" + contactId + ".txt").toPath(); 
            // creates path object, to
          // be more specific so less
          // errors
        Charset charset = Charset.forName("UTF-8"); // specifies char encoding to read the file as UTF-8. If anything
        // other than UTF-8 is found will have error

        List<String> stringList = new ArrayList<>();
        try {
            stringList = Files.readAllLines(filePath, charset);
            // set based on form structure in the homepage
            ctc.setId(contactId);
            ctc.setName(stringList.get(0));
            ctc.setEmail(stringList.get(1));
            ctc.setPhoneNumber(stringList.get(2));
            // parse string to local date object using date from form and to format to
            // desired year month date
            LocalDate dob = LocalDate.parse(stringList.get(3), formatter);
            // only after parsing then can set dob
            ctc.setDateOfBirth(dob);

        } catch (IOException e) {
            e.printStackTrace();
            // return null to indicate that operation was unsuccessful
            return null;
        }
        // if exception occurs, return ctc will not run, it will return null instead
        return ctc;

    }
}
