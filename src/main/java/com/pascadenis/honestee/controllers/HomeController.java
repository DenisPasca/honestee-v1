package com.pascadenis.honestee.controllers;

import com.pascadenis.honestee.entities.Products;
import com.pascadenis.honestee.services.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;


@Controller
public class HomeController {

    private final ProductsService productsService;

    public HomeController(ProductsService productsService) {
        this.productsService = productsService;
    }

@GetMapping("/")
public String home()
{
        return "home";
}

    @RequestMapping("/index")
    public String index(Model model, Principal principal, HttpSession session) {

        if(principal!=null) {
            session.setAttribute("username",principal.getName());
        } else {

            session.removeAttribute("username");
        }

        return "home";
    }

    @GetMapping("/about")
    public String about() {

        return "about";
    }

    @GetMapping("/faq")
    public String faq() {

        return "faq";
    }

    @GetMapping("/contact")
    public String contact() {

        return "contact";
    }
}
