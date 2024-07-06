package com.luma.api.services;

import com.luma.api.payloads.addItemsToCartPayload.AddItemsToCartPayLoad;

import static com.luma.api.MagentoApi.BASE_URL_REST;

public class AddItemsToCartApiService extends ApiService {

    public void addingProductToCart(AddItemsToCartPayLoad addItemsToCartPayLoad, String token) {
        setup()
                .auth().oauth2(token)
                .body(addItemsToCartPayLoad)
                .post(BASE_URL_REST + "carts/mine/items")
                .then()
                .assertThat().statusCode(200);


    }
}
