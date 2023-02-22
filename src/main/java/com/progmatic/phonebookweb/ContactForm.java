package com.progmatic.phonebookweb;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ContactForm {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^\\d{2}-?\\d{2}/?\\d{4,9}$")
    private String tel;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
