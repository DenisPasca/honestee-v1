package com.pascadenis.honestee.services;

import com.pascadenis.honestee.entities.Products;
import com.pascadenis.honestee.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Products products) {


        productRepository.save(products);
    }

    public List<Products> findAll() {

        return productRepository.findAll();
    }


    public Products getProductById(Long id) {

        return productRepository.findById(id).get();

    }

    public Products updateProduct(Products product) {
            return productRepository.save(product);
    }

    public void deleteProduct(Long id) {

         productRepository.deleteById(id);

         productRepository.findAll(PageRequest.of(3,5));
    }


//    public Page<Products> pageProducts(int pageNo) {
//
//        Pageable pageable = PageRequest.of(pageNo,3);
//
//
//        return productPage;
//    }

    public Page<Products> productsPage(int page) {

        Pageable pageable = PageRequest.of(page-1,6);

       return productRepository.findAll(pageable);
    }

    public Page<Products> searchProduct(int pageNo,String keyword) {
        Pageable pageable=PageRequest.of(pageNo,50);
        return productRepository.findByNameContaining(keyword,pageable);


    }




}
