package com.luma.api;

import com.luma.api.payloads.tokenPayLoad.TokenPayLoad;
import com.luma.api.payloads.updateUserPayLoad.UpdateUserPayLoad;
import com.luma.api.payloads.userPayLoad.Customer;
import com.luma.api.services.TokenApiService;
import com.luma.api.services.UserApiService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.luma.data.TestData.ADMIN;
import static com.luma.data.TestData.ADMIN_PASS;
import static com.luma.data.TestData.FIRST_NAME;
import static com.luma.data.TestData.GROUP_ID;
import static com.luma.data.TestData.LAST_NAME;
import static com.luma.data.TestData.PASSWORD;
import static com.luma.data.TestData.WEBSITE_ID;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class UpdateUserDataTest {

    private final Customer customer = new Customer();
    private final UserApiService userApiService = new UserApiService();
    private final TokenPayLoad tokenPayLoad = new TokenPayLoad();
    private final TokenApiService tokenApiService = new TokenApiService();
    private final UpdateUserPayLoad updateUserPayLoad = new UpdateUserPayLoad();

    //    @Test
    public void testCanUpdateUserFirstNameAndLastName() {

        String randEmail = "automation_" + randomAlphanumeric(3) + "@google.com";
        String updated_firstname = "Automation_Updated";
        String updated_lastname = "Test_Updated";

        customer.create(FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);
        tokenPayLoad.username(ADMIN).password(ADMIN_PASS);
        String token = tokenApiService.getAdminToken(tokenPayLoad);

        int userId = userApiService.registerNewUser(customer)
                .then()
                .assertThat().statusCode(200)
                .and()
                .extract().response().body().jsonPath().get("id");

        updateUserPayLoad.addDataForUpdateUser(userId, randEmail, updated_firstname, updated_lastname, WEBSITE_ID, GROUP_ID);
        String result = userApiService.updateUser(token, updateUserPayLoad, String.valueOf(userId))
                .then()
                .statusCode(200)
                .and()
                .extract().response().jsonPath().get("\"firstname\"");
        Assert.assertEquals(result, updated_firstname);
    }

    @Test
    public void testCanAddAddressForUser() {

        String randEmail = "automation_" + randomAlphanumeric(3) + "@google.com";
        customer.create(FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);


        int userId = userApiService.registerNewUser(customer)
                .then()
                .assertThat().statusCode(200)
                .and()
                .extract().response().body().jsonPath().get("id");
        tokenPayLoad.username(randEmail).password(PASSWORD);
    }
}
