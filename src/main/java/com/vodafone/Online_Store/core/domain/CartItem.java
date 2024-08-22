package com.vodafone.Online_Store.core.domain;

import lombok.Data;

@Data
public class CartItem {

    private Long productId;
    private int quantity;

    public CartItem(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }


}
