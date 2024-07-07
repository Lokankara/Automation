package com.luma.api.services;

import com.luma.api.payloads.updateUserPayLoad.UpdateUserPayLoad;
import com.luma.api.payloads.userPayLoad.Customer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.luma.api.services.MagentoApiService.BASE_URL_REST;

public class UserApiService extends ApiService {

    @Step("registration a new user")
    public Response registerNewUser(Customer customer) {
        return setup()
                .body(customer)
                .when()
                .post(BASE_URL_REST + "customers");
    }

    public Response deleteUser(String token, String userId) {
        return setup()
                .auth().oauth2(token)
                .delete(BASE_URL_REST + "customers/" + userId);
    }

    public Response updateUser(String token, UpdateUserPayLoad updateUserPayLoad, String userId) {
        return setup()
                .auth().oauth2(token)
                .body(updateUserPayLoad)
                .put(BASE_URL_REST +  "customers/" + userId);
    }
}
