package com.pascadenis.honestee.repositories;

import com.pascadenis.honestee.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {


}
