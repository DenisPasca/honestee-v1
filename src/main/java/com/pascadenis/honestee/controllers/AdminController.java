package com.pascadenis.honestee.controllers;

import com.pascadenis.honestee.entities.CartItem;
import com.pascadenis.honestee.entities.Products;
import com.pascadenis.honestee.entities.ShoppingCart;
import com.pascadenis.honestee.entities.Users;
import com.pascadenis.honestee.repositories.CartRepository;
import com.pascadenis.honestee.repositories.ShoppingCartRepository;
import com.pascadenis.honestee.repositories.UserRepository;
import com.pascadenis.honestee.services.ProductsService;
import com.pascadenis.honestee.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {


   @Autowired
   private ShoppingCartService cartService;

   @Autowired
   private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartRepository cartRepository;

    private final ProductsService productsService;

    private final UserRepository userRepository;

    public AdminController(ProductsService productsService, UserRepository userRepository) {
        this.productsService = productsService;
        this.userRepository = userRepository;
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {

        model.addAttribute("product",new Products());

        return "add-product";
    }

    @PostMapping("/addProduct")
    public String addProductAction (@ModelAttribute("product") Products product) {

        productsService.addProduct(product);

        return "redirect:/products/testare/1";
    }

    @GetMapping("/editproduct")
    public String displayProducts(Model model) {

        List<Products> produse =productsService.findAll();

        model.addAttribute("products",produse);

        return "products.html";

    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model) {

        model.addAttribute("product",productsService.getProductById(id));

        return "edit-product";

    }


    @PostMapping("/{id}")
    public String editProduct(@PathVariable Long id,
                              @ModelAttribute("product") Products product) {

        Products oldProduct = productsService.getProductById(id);

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setColor(product.getColor());
        oldProduct.setImageSrc(product.getImageSrc());
        oldProduct.setId(id);

        productsService.updateProduct(oldProduct);


        return "redirect:/products/testare/1";

    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {

        model.addAttribute("product",productsService.getProductById(id));

        return "delete-product";

    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {


        productsService.deleteProduct(id);


        return "redirect:/products/testare/1";

    }






}
