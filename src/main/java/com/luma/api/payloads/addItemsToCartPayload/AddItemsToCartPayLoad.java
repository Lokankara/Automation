package com.luma.api.payloads.addItemsToCartPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class AddItemsToCartPayLoad {

    @JsonProperty("cartItem")
    private CartItem cartItem;

    @Override
    public String toString() {
        return "" + cartItem;
    }
    public AddItemsToCartPayLoad setProductToCart(String productSku, int qty, String quoteId){
        return  cartItem(new CartItem()
                .sku(productSku)
                .qty(1)
                .quoteId(quoteId));
    }
}