package com.luma.api;

import com.luma.api.payloads.tokenPayLoad.TokenPayLoad;
import com.luma.api.payloads.userPayLoad.Customer;
import com.luma.api.services.TokenApiService;
import com.luma.api.services.UserApiService;
import com.luma.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class RegistrationUserTest extends BaseTest {

    private final Customer customer = new Customer();
    private final UserApiService userApiService = new UserApiService();
    private final TokenPayLoad tokenPayLoad = new TokenPayLoad();
    private final TokenApiService tokenApiService = new TokenApiService();

    @Test(groups = "smoke")
    public void testCanRegisterNewUser() {
        String randEmail = "automation_" + randomAlphanumeric(3) + "@google.com";
        customer.setNewCustomerData(randEmail, FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);
        userApiService.registerNewUser(customer)
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void testCanNotRegisterNewUserWithoutData() {
        customer.setNewCustomerData("", "", "", WEBSITE_ID, GROUP_ID, PASSWORD);
        String message = userApiService
                .registerNewUser(customer)
                .then()
                .assertThat().statusCode(400)
                .and()
                .extract().response().getBody().asString();
        Assert.assertEquals(message, "{\"message\":\"The customer email is missing. Enter and try again.\"}");
    }

    @Test
    public void testCanNotRegisterNewUserTwice() {
        String randEmail = "automation_" + randomAlphanumeric(3) + "@google.com";
        customer.setNewCustomerData(randEmail, FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);
        userApiService.registerNewUser(customer);
        String message = userApiService.registerNewUser(customer)
                .then()
                .assertThat().statusCode(400)
                .and()
                .extract().response().getBody().asString();
        Assert.assertEquals(message, "{\"message\":\"A customer with the same email address already exists in an associated website.\"}");

    }

//    @Test
    public void testCanDeleteUser() {
        String randEmail = "automation_" + randomAlphanumeric(3) + "@google.com";
        customer.setNewCustomerData(randEmail, FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);
        tokenPayLoad.username(ADMIN).password(ADMIN_PASS);
        String token = tokenApiService.getAdminToken(tokenPayLoad);
        int userId = userApiService.registerNewUser(customer)
                .then()
                .assertThat().statusCode(200)
                .and()
                .extract().response().body().jsonPath().get("id");

        String result = userApiService.deleteUser(token, String.valueOf(userId))
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        Assert.assertEquals(result, "true");
    }
}
