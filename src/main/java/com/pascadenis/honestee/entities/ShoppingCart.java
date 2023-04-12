package com.pascadenis.honestee.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private long id;

    private int totalItems;

    private double totalPrice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="cart" )
    private Set<CartItem> cartItems;
}


