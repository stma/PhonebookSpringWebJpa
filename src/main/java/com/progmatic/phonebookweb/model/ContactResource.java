package com.progmatic.phonebookweb.model;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ContactResource {

    private List<Contact> contacts = new LinkedList<>();

    public Contact getContact(String name) {
        Contact res = null;
        for (Contact c: contacts) {
            if (c.name().equalsIgnoreCase(name)) {
                res = c;
                break;
            }
        }
        return res;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public boolean addContact(Contact c) {
        return contacts.add(c);
    }

    public boolean removeContact(Contact c) {
        return contacts.remove(c);
    }
    public boolean modifyContact(Contact c) {
        Contact oldContact = this.getContact(c.name());
        if (oldContact != null) {
            this.removeContact(oldContact);
            return this.addContact(c);
        }
        return false;
    }
}
