package com.progmatic.phonebookweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.HttpMessageEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @GetMapping(path = "/registration")
    public String regPage(
            Model m,
            Principal principal
    ) {
        if (principal != null) {
            return "redirect:/logout";
        }

        m.addAttribute("user", new RegForm());
        return "reg-form";
    }

    @Transactional
    @PostMapping(path = "/registration")
    public String regPagePost(
            @ModelAttribute("user") @Validated RegForm user,
            BindingResult binds
    ) {
        if (binds.hasErrors()) {
            return "reg-form";
        }

        userDetailsManager.createUser(
                User.builder()
                        .username(user.getUsername())
                        .roles("USER")
                        .password("{bcrypt}" + new BCryptPasswordEncoder().encode(user.getPassword()))
                        .build()
        );
        return "redirect:/reg-successful";
    }

    @GetMapping("/change-password")
    public String pwChangeForm() {
        return "chpw";
    }

    @PostMapping("/change-password")
    public String pwChange(
            @RequestParam("old_password") String opw,
            @RequestParam("new_password") String npw,
            @RequestParam("new_password_again") String npwa,
            Principal principal
    ) {
        if (!npwa.equals(npw)) {
            String error = UriUtils.encode("Nem azonosak az új jelszavak.", StandardCharsets.UTF_8);
            return "redirect:/change-password?error=" + error;
        }

        UserDetails user = userDetailsManager.loadUserByUsername(principal.getName());


        try {
            userDetailsManager.updateUser(
                    User.withUserDetails(user)
                            .password("{bcrypt}" + new BCryptPasswordEncoder().encode(npw))
                            .build()
            );
        } catch (AuthenticationException e) {
            String error = UriUtils.encode("A régi jelszó nem egyezik.", StandardCharsets.UTF_8);
            return "redirect:/change-password?error=" + error;
        }
        return "redirect:/change-password-success";
    }

    @GetMapping("/change-password-success")
    public String pwChangeSuccess() {
        return "change-password-success";
    }

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
