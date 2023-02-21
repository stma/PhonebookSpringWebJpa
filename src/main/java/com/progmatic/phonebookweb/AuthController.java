package com.progmatic.phonebookweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//
//    @GetMapping("/login")
//    public String loginForm() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(
//            @RequestParam("name") String userName,
//            @RequestParam("pw") String password
//    ) {
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                userName, password
//        );
//        authenticationManager.authenticate(authentication);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        if (authentication.isAuthenticated()) {
//            return "redirect:/new";
//        }
//        return "login";
//    }
}
