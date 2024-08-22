package com.vodafone.Online_Store.core.service;



import com.vodafone.Online_Store.core.domain.Cart;
import com.vodafone.Online_Store.core.domain.CartItem;

public interface CartService {
    Cart getCartByUserId(Long userId);
    void addItemToCart(Long userId, CartItem item);
    void removeItemFromCart(Long userId, CartItem item);
    void clearCart(Long userId);
}

