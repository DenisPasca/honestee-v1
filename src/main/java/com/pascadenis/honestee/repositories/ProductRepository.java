package com.pascadenis.honestee.repositories;

import com.pascadenis.honestee.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Long> {

//    @Query("""
//            SELECT p FROM Products p WHERE p.name LIKE '%1%'
//            """)
    Page<Products> findByNameContaining(String name, Pageable pageable);




}
