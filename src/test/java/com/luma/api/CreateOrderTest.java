package com.luma.api;

import com.luma.api.payloads.addItemsToCartPayload.AddItemsToCartPayLoad;
import com.luma.api.payloads.createOrderPayLoad.CreateOrderPayLoad;
import com.luma.api.payloads.prepareCheckoutPayLoad.PrepareCheckoutPayLoad;
import com.luma.api.payloads.tokenPayLoad.TokenPayLoad;
import com.luma.api.payloads.userPayLoad.Customer;
import com.luma.api.services.AddItemsToCartApiService;
import com.luma.api.services.CreateOrderApiService;
import com.luma.api.services.PrepareCheckoutApiService;
import com.luma.api.services.QuoteApiService;
import com.luma.api.services.TokenApiService;
import com.luma.api.services.UserApiService;
import com.luma.runner.BaseTest;
import io.qameta.allure.Epic;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Epic("Create Order")
public class CreateOrderTest extends BaseTest {

    private final Customer customer = new Customer();
    private final UserApiService userApiService = new UserApiService();
    private final TokenApiService tokenApiService = new TokenApiService();
    private final TokenPayLoad tokenPayLoad = new TokenPayLoad();
    private final QuoteApiService quoteApiService = new QuoteApiService();
    private final AddItemsToCartPayLoad addItemsToCartPayLoad = new AddItemsToCartPayLoad();
    private final AddItemsToCartApiService addItemsToCartApiService = new AddItemsToCartApiService();
    private final CreateOrderPayLoad createOrderPayLoad = new CreateOrderPayLoad();
    private final CreateOrderApiService createOrderApiService = new CreateOrderApiService();
    private final PrepareCheckoutPayLoad prepareCheckoutPayLoad = new PrepareCheckoutPayLoad();
    private final PrepareCheckoutApiService prepareCheckoutApiService = new PrepareCheckoutApiService();


    @Test
    public void testCanPlaceOrder() {
        String randEmail = "automation_" + randomAlphanumeric(3) + "@google.com";
        String productSku = "24-WB04";
        int productQty = 1;

        String region = "New York";
        int regionId = 43;
        String regionCode = "NY";
        String countryCode = "US";
        List<String> street = singletonList("123 Oak Ave");
        String postCode = "10577";
        String city = "Purchase";
        String telephone = "512-555-1111";
        String shippingMethod = "flatrate";
        String shippingCarrierCode = "flatrate";
        String paymentSystem = "checkmo";

        customer.setNewCustomerData(randEmail, FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);

        tokenPayLoad.username(randEmail).password(PASSWORD);

        prepareCheckoutPayLoad.setCheckoutData(region,
                regionId,
                regionCode,
                countryCode,
                street,
                postCode,
                city,
                FIRST_NAME,
                LAST_NAME,
                randEmail,
                telephone,
                shippingMethod,
                shippingCarrierCode);

        createOrderPayLoad.setPaymentrData(paymentSystem,
                randEmail,
                region,
                regionId,
                regionCode,
                countryCode,
                street,
                postCode,
                city,
                FIRST_NAME,
                LAST_NAME,
                telephone);

        userApiService.registerNewUser(customer);
        String token = tokenApiService.getUserToken(tokenPayLoad);
        String quoteId = quoteApiService.getQuoteId(token);
        addItemsToCartPayLoad.setProductToCart(productSku, productQty, quoteId);
        addItemsToCartApiService.addingProductToCart(addItemsToCartPayLoad, token);
        prepareCheckoutApiService.setShippingInformation(token, prepareCheckoutPayLoad);

        createOrderApiService.placeOrder(token, createOrderPayLoad)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body(Matchers.not(Matchers.isEmptyString()));
    }
}
