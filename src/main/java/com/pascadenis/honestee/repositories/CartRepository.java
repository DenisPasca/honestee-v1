package com.pascadenis.honestee.repositories;

import com.pascadenis.honestee.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem,Long> {


    // CartItem newItem(product,quantity) {

//          double totalPrice = quantity*product.getPrice();
    //       newItem(product,quantity,totalPrice)
    //        repo.save(newItem)
}
