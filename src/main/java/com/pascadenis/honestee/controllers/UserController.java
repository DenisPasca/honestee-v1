package com.pascadenis.honestee.controllers;


import com.pascadenis.honestee.entities.Users;
import com.pascadenis.honestee.services.AuthorityService;
import com.pascadenis.honestee.services.JpaUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {


    private final JpaUserDetailsService jpaUserDetailsService;
    private final AuthorityService authorityService;


    public UserController(JpaUserDetailsService jpaUserDetailsService, AuthorityService authorityService) {
        this.jpaUserDetailsService = jpaUserDetailsService;

        this.authorityService = authorityService;
    }


    @GetMapping("/proceedRegister")
    private String registerUser(Model model) {

        model.addAttribute("user", new Users());

        return "signup";


    }


    @PostMapping("/register-new")
    private String register(@Valid @ModelAttribute("user") Users user,
                            Model model) {


        try {


            Users test = jpaUserDetailsService.findUser(user.getUsername());


            if (test != null) {

                model.addAttribute("userExists", "Username already in use.");
                return "signup";
            }

            if (user.getPassword().equals(user.getRepeatPassword())) {

                user.setAuthorities(authorityService.findByName("read"));
                jpaUserDetailsService.addUser(user);
                model.addAttribute("success", "Account successfully created");
                model.addAttribute("user", user);

            } else {

                model.addAttribute("user", user);
                model.addAttribute("samePassword", "The passwords must be the same!");

                return "signup";
            }


        } catch (Exception e) {

            e.printStackTrace();
            model.addAttribute("error", "Something went wrong :(");


        }



        return "signup";

    }

    @GetMapping("/login")
    private String login() {

        return "sign-in";
    }
}
