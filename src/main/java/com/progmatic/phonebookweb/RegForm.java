package com.progmatic.phonebookweb;

import jakarta.validation.constraints.Size;

public class RegForm {

    @Size(max = 30, min = 4)
    private String username;
    @Size(max = 30, min = 8)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
