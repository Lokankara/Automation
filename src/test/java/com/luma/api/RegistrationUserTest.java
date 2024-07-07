package com.luma.api;

import com.github.javafaker.Faker;
import com.luma.api.payloads.userPayLoad.Customer;
import com.luma.api.services.UserApiService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.luma.data.TestData.FIRST_NAME;
import static com.luma.data.TestData.GROUP_ID;
import static com.luma.data.TestData.LAST_NAME;
import static com.luma.data.TestData.PASSWORD;
import static com.luma.data.TestData.TOKEN;
import static com.luma.data.TestData.WEBSITE_ID;

public class RegistrationUserTest {
    private final UserApiService userApiService = new UserApiService();

    @Test(groups = "smoke")
    public void testCanRegisterNewUser() {
        Customer customer = new Customer().create(FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);

        userApiService.registerNewUser(customer)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testCanNotRegisterNewUserWithoutData() {
        Customer customer = new Customer().create("", "", "", WEBSITE_ID, GROUP_ID, PASSWORD);

        String message = userApiService
                .registerNewUser(customer)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .extract()
                .response()
                .getBody()
                .asString();

        Assert.assertEquals(message, "{\"message\":\"The customer email is missing. Enter and try again.\"}");
    }

    @Test
    public void testCanNotRegisterNewUserTwice() {
        Customer customer = new Customer().create(FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);

        userApiService.registerNewUser(customer);
        String message = userApiService.registerNewUser(customer)
                .then()
                .assertThat().statusCode(400)
                .and()
                .extract().response().getBody().asString();
        Assert.assertEquals(message, "{\"message\":\"A customer with the same email address already exists in an associated website.\"}");

    }

    @Test
    public void testCanDeleteUser() {
        String firstName = Faker.instance().name().firstName();
        String lastName = Faker.instance().name().lastName();
        Customer customer = new Customer().create(firstName, lastName, WEBSITE_ID, GROUP_ID, PASSWORD);

        int userId = userApiService.registerNewUser(customer)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .response()
                .body()
                .jsonPath()
                .get("id");

        String result = userApiService
                .deleteUser(TOKEN, String.valueOf(userId))
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        Assert.assertEquals(result, "true");
    }
}
