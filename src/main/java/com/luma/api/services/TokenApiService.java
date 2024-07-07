package com.luma.api.services;

import com.luma.api.payloads.tokenPayLoad.TokenPayLoad;
import io.qameta.allure.Step;

import static com.luma.api.services.MagentoApiService.BASE_URL_REST;

public class TokenApiService extends ApiService {

    @Step("get user token for {token}")
    public String getUserToken(TokenPayLoad token) {
        return setup()
                .body(token)
                .post(BASE_URL_REST + "integration/customer/token")
                .then()
                .extract().response().asString().replace("\"", "");
    }
    @Step("get admin token for {token}")
    public String getAdminToken(TokenPayLoad token) {
        return setup()
                .body(token)
                .post(BASE_URL_REST + "integration/admin/token")
                .then()
                .extract().response().asString().replace("\"", "");
    }
}
