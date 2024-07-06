package com.luma.api.services;

import com.luma.api.payloads.prepareCheckoutPayLoad.PrepareCheckoutPayLoad;
import io.qameta.allure.Step;

import static com.luma.api.MagentoApi.BASE_URL_REST;

public class PrepareCheckoutApiService extends ApiService {
    @Step("Setting shipping information")
    public void setShippingInformation(String token, PrepareCheckoutPayLoad prepareCheckoutPayLoad){
        setup()
                .auth().oauth2(token)
                .body(prepareCheckoutPayLoad)
                .post(BASE_URL_REST + "carts/mine/shipping-information")
                .then().statusCode(200);

    }

}
