package com.progmatic.phonebookweb;

import com.progmatic.phonebookweb.model.Contact;
import com.progmatic.phonebookweb.model.ContactResource;
//import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String addContactPage(Model m) {
        m.addAttribute("newContact", new ContactForm());
        return "add-contact";
    }

    @PostMapping(path = "/new")
    public String saveContact(
        @Validated
//            @Valid
        @ModelAttribute("newContact")
        ContactForm newContact,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "add-contact";
        }

        Contact c = new Contact(newContact.getName(), newContact.getEmail(), newContact.getTel());
        if (contactResource.addContact(c)) {
            model.addAttribute("contact", c);
            return "success";
        } else {
            throw new RuntimeException("Elszallt");
        }
    }

    @GetMapping("/contact/{cId}")
    public String showContactData(
            @PathVariable("cId") String userName,
            Model model
    ) {
        Contact contact = contactResource.getContact(userName);
        if (contact == null) {
            model.addAttribute("cId", userName);
            return "show-null-contact";
        }

        model.addAttribute("c", contact);
        return "show-contact";
    }

//    @GetMapping(path = "/all-contacts")
//    @GetMapping("/all-contacts")
    @RequestMapping(path = "/all-contacts", method = RequestMethod.GET)
    public String getAllContact(Model model) {
        List<Contact> all = contactResource.getContacts();
        model.addAttribute("contacts", all);
        return "allcontacts";
    }
//
//    @GetMapping("/alma")
//    public String getKacsa(Model m) {
//        m.addAttribute("name", "Matyi");
//        return "linkpl";
//    }
}
