package com.pascadenis.honestee.controllers;

import com.pascadenis.honestee.entities.Products;
import com.pascadenis.honestee.entities.ShoppingCart;
import com.pascadenis.honestee.entities.Users;
import com.pascadenis.honestee.services.JpaUserDetailsService;
import com.pascadenis.honestee.services.ProductsService;
import com.pascadenis.honestee.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    private JpaUserDetailsService userService;

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private ProductsService productsService;

    @GetMapping("/cart")
    public String cart(Model model,
                       Principal principal) {

        if(principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        Users user = userService.findUser(username);



        ShoppingCart shoppingCart = user.getShoppingCart();

        if(shoppingCart==null) {
            model.addAttribute("check","No item in your cart");
        }

        model.addAttribute("shoppingCart",shoppingCart);


        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(HttpServletRequest request,
                                @RequestParam("id") Long productId,
                                @RequestParam(value = "quantity" ,required = false,defaultValue = "1") int quantity,
                                Principal principal ) {

        if(principal==null) {

            return "redirect:/login";
        }
        Products product =productsService.getProductById(productId);

        String username=principal.getName();
        Users user= userService.findUser(username);



        ShoppingCart cart = cartService.addItemToCart(product,quantity,user);


        return "redirect:" +request.getHeader("Referer");



    }


    @RequestMapping(value="/update-cart",method = RequestMethod.POST,params="action=update")
    public String updateCart(@RequestParam("id") Long productId,
                             @RequestParam("quantity") int quantity,
                             Model model,
                             Principal principal) {

        if(principal == null) {

            return "redirect:/login";
        }

        String username=principal.getName();
        Users user = userService.findUser(username);
        Products product=productsService.getProductById(productId);

        ShoppingCart cart=cartService.updateItemInCart(product,quantity,user);

        model.addAttribute("shoppingCart",cart);

        return "redirect:/cart";


    }

    @RequestMapping(value="/update-cart",method = RequestMethod.POST,params="action=delete")
    public String deleteCart(@RequestParam("id") Long productId,
                             Model model,
                             Principal principal) {

        if(principal == null) {

            return "redirect:/login";
        }

        String username=principal.getName();
        Users user = userService.findUser(username);
        Products product=productsService.getProductById(productId);

       ShoppingCart cart = cartService.deleteItemFromCart(product,user);


        model.addAttribute("shoppingCart",cart);

        return "redirect:/cart";


    }

    @GetMapping("/checkout")
    public String checkout(Principal principal,
                           Model model){

        if(principal==null) {
            return "redirect:/login";
        }

        String username=principal.getName();
        Users user = userService.findUser(username);
        ShoppingCart shoppingCart = user.getShoppingCart();

        if(shoppingCart==null) {
            model.addAttribute("check","No items in your shoppingCart");
        }

        model.addAttribute("shoppingCart",shoppingCart);


        return "checkout";

    }


}
