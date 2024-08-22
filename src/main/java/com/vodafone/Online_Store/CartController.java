package com.vodafone.Online_Store;


import com.vodafone.Online_Store.core.domain.Cart;
import com.vodafone.Online_Store.core.domain.CartItem;
import com.vodafone.Online_Store.core.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{userId}")
    public void addItemToCart(@PathVariable Long userId, @RequestBody CartItem item) {
        cartService.addItemToCart(userId, item);
    }

    @DeleteMapping("/{userId}")
    public void removeItemFromCart(@PathVariable Long userId, @RequestBody CartItem item) {
        cartService.removeItemFromCart(userId, item);
    }

    @DeleteMapping("/{userId}/clear")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}

