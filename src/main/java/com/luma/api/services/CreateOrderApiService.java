package com.luma.api.services;

import com.luma.api.payloads.createOrderPayLoad.CreateOrderPayLoad;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.luma.api.services.MagentoApiService.BASE_URL_REST;

public class CreateOrderApiService extends ApiService {

    @Step("Placing order")
    public Response placeOrder(String token, CreateOrderPayLoad createOrderPayLoad) {
        return setup()
                .auth().oauth2(token)
                .body(createOrderPayLoad)
                .post(BASE_URL_REST + "carts/mine/payment-information")
                .then()
                .extract().response();
    }
}
