package com.vodafone.Online_Store.core.service;




import com.vodafone.Online_Store.core.domain.Cart;
import com.vodafone.Online_Store.core.domain.CartItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private Map<Long, Cart> cartDatabase = new HashMap<>();

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartDatabase.getOrDefault(userId, new Cart());
    }

    @Override
    public void addItemToCart(Long userId, CartItem item) {
        Cart cart = cartDatabase.getOrDefault(userId, new Cart());
        cart.addItem(item);
        cartDatabase.put(userId, cart);
    }

    @Override
    public void removeItemFromCart(Long userId, CartItem item) {
        Cart cart = cartDatabase.get(userId);
        if (cart != null) {
            cart.removeItem(item);
        }
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartDatabase.get(userId);
        if (cart != null) {
            cart.clearCart();
        }
    }
}

