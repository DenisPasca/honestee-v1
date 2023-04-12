package com.pascadenis.honestee.services;

import com.pascadenis.honestee.entities.CartItem;
import com.pascadenis.honestee.entities.Products;
import com.pascadenis.honestee.entities.ShoppingCart;
import com.pascadenis.honestee.entities.Users;
import com.pascadenis.honestee.repositories.CartRepository;
import com.pascadenis.honestee.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartRepository cartRepository;

    public ShoppingCart addItemToCart(Products products, int quantity, Users user) {

        ShoppingCart cart = user.getShoppingCart();

        if(cart==null) {
            cart=new ShoppingCart();
        }

        Set<CartItem> cartItems = cart.getCartItems();

        CartItem cartItem=findCartItem(cartItems,products.getId());

        if(cartItems==null) {
            cartItems = new HashSet<>();

            if(cartItem==null) {

                cartItem = new CartItem();
                double totalPrice=quantity*products.getPrice();
                cartItem.setProduct(products);
                cartItem.setTotal_price(totalPrice);
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                cartRepository.save(cartItem);
            }

        } else {

            if(cartItem==null) {

                cartItem = new CartItem();
                double totalPrice=quantity*products.getPrice();
                cartItem.setProduct(products);
                cartItem.setTotal_price(totalPrice);
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                cartRepository.save(cartItem);
            } else {

                cartItem.setQuantity(cartItem.getQuantity()+quantity);
                cartItem.setTotal_price(cartItem.getTotal_price()+(quantity*products.getPrice()));

            }

        }

        cart.setCartItems(cartItems);
        int totalItems=totalItems(cart.getCartItems());
        double totalPrice=totalPrice(cart.getCartItems());

        int totalQuantity= (int) cart.getCartItems().stream().map(i->i.getQuantity()).count();

        cart.setTotalPrice(totalPrice);
        cart.setTotalItems(totalQuantity);
        cart.setUser(user);



        return shoppingCartRepository.save(cart);

    }

    public CartItem findCartItem(Set<CartItem> cartItems,Long productId) {

        if(cartItems==null) {
            return null;
        }

        CartItem cartItem = null;

        for(CartItem item: cartItems) {

            if(item.getProduct().getId()==productId) {

                cartItem=item;
            }
        }

        return cartItem;

    }

    private int totalItems(Set<CartItem> cartItems) {

        int totalItems=0;
        for(CartItem item: cartItems) {
            totalItems+=item.getQuantity();
        }

        return totalItems;
    }

    private double totalPrice(Set<CartItem> cartItems) {

        double totalPrice=0;
        for(CartItem item:cartItems) {

            totalPrice+=item.getTotal_price();
        }

        return totalPrice;
    }

    public ShoppingCart updateItemInCart(Products product,int quantity, Users user) {

        ShoppingCart cart = user.getShoppingCart();

        Set<CartItem> cartItems = cart.getCartItems();

        CartItem item = findCartItem(cartItems,product.getId());

        item.setQuantity(quantity);
        item.setTotal_price(quantity*product.getPrice());

        cartRepository.save(item);


        int totalItems=totalItems(cartItems);
        double totalPrice=totalPrice(cartItems);

        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);

        return shoppingCartRepository.save(cart);


    }

   public ShoppingCart deleteItemFromCart(Products product,Users user) {

       ShoppingCart cart = user.getShoppingCart();

       Set<CartItem> cartItems = cart.getCartItems();

       CartItem item = findCartItem(cartItems,product.getId());

       cartItems.remove(item);

       cartRepository.delete(item);

       int totalItems=totalItems(cartItems);
       double totalPrice=totalPrice(cartItems);

       cart.setCartItems(cartItems);
       cart.setTotalItems(totalItems);
       cart.setTotalPrice(totalPrice);

       return shoppingCartRepository.save(cart);


    }
}
