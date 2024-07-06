package com.luma.api.services;

import io.qameta.allure.Step;

import static com.luma.api.MagentoApi.BASE_URL_REST;

public class QuoteApiService extends ApiService {
    @Step("Getting quote id")
    public String getQuoteId(String token) {
        return setup()
                .auth().oauth2(token)
                .post(BASE_URL_REST + "carts/mine")
                .then()
                .extract().response().asString();
    }
}
