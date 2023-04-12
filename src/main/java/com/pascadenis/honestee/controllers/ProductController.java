package com.pascadenis.honestee.controllers;

import com.pascadenis.honestee.entities.Products;
import com.pascadenis.honestee.services.ProductsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @GetMapping("/all")
    @ResponseBody
    public Iterable<Products> getAllProducts() {
        return productsService.findAll();
    }




    @GetMapping("/testare/{page}")
    public String cardProducts(Model model, @PathVariable("page")   int page)  {

        Page<Products> pagina = productsService.productsPage(page);
        int totalPages = pagina.getTotalPages();
        long totalItems = pagina.getTotalElements();
        List<Products> produse = pagina.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("produse", produse);


        return "cardTest";


    }







    @GetMapping("/test/page/{page}")
    public String getOnePage(Model model, @PathVariable("page") int page) {

        Page<Products> pagina = productsService.productsPage(page);
        int totalPages = pagina.getTotalPages();
        long totalItems = pagina.getTotalElements();
        List<Products> produse = pagina.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("produse", produse);

        return "cardTest";


    }

    @GetMapping("/test")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/search-result/{pageNo}")
    public String searchResult(@PathVariable("pageNo") int pageNo,
                               Model model,
                               @RequestParam(value = "name",required = false) String name) {

        Page<Products> produse=productsService.searchProduct(pageNo,name);



        if(produse.isEmpty()) {
            model.addAttribute("noResult", "No results based on the search term");
        }

        model.addAttribute("produse",produse);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages", produse.getTotalPages());




        return "search-result";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetails(Model model,@PathVariable("id") Long id) {

        Products product =productsService.getProductById(id);

        model.addAttribute("product",product);

        return "product-detail";
    }


}
