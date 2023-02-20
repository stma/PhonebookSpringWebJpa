package com.progmatic.phonebookweb;

import com.progmatic.phonebookweb.model.Contact;
import com.progmatic.phonebookweb.model.ContactResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PhoneBookController {

    private ContactResource contactResource;

    public PhoneBookController(ContactResource contactResource) {
        this.contactResource = contactResource;
    }

    @GetMapping(path = {"", "/", "/home"})
    public String getHomePage() {
        return "home";
    }

    @GetMapping(path = "/new")
    public String addContactPage() {
        return "add-contact";
    }

    @PostMapping(path = "/new")
    public String saveContact(
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("tel") String tel,
        Model model
    ) {
        Contact c = new Contact(name, email, tel);
        if (contactResource.addContact(c)) {
            model.addAttribute("contact", c);
            return "success";
        } else {
            throw new RuntimeException("Elszallt");
        }
    }

    @GetMapping(path = "/all-contacts")
    public String getAllContact(Model model) {
        List<Contact> all = contactResource.getContacts();
        model.addAttribute("contacts", all);
        return "allcontacts";
    }
}
