package com.luma.api;

import com.luma.api.payloads.addItemsToCartPayload.AddItemsToCartPayLoad;
import com.luma.api.payloads.addressPayLoad.AddressPayLoad;
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
import io.qameta.allure.Epic;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.luma.data.TestData.FIRST_NAME;
import static com.luma.data.TestData.GROUP_ID;
import static com.luma.data.TestData.LAST_NAME;
import static com.luma.data.TestData.PASSWORD;
import static com.luma.data.TestData.WEBSITE_ID;

@Epic("Create Order")
public class CreateOrderTest {
    private final UserApiService userApiService = new UserApiService();
    private final TokenApiService tokenApiService = new TokenApiService();
    private final TokenPayLoad tokenPayLoad = new TokenPayLoad();
    private final QuoteApiService quoteApiService = new QuoteApiService();
    private final AddItemsToCartPayLoad addItemsToCartPayLoad = new AddItemsToCartPayLoad();
    private final AddItemsToCartApiService addItemsToCartApiService = new AddItemsToCartApiService();
    private final CreateOrderApiService createOrderApiService = new CreateOrderApiService();
    private final PrepareCheckoutApiService prepareCheckoutApiService = new PrepareCheckoutApiService();

    @Test
    public void testCanPlaceOrder() {
        int productQty = 1;
        String productSku = "24-WB04";
        String shippingMethod = "flatrate";
        String shippingCarrierCode = "flatrate";
        String paymentSystem = "checkmo";

        Customer customer = new Customer().create(FIRST_NAME, LAST_NAME, WEBSITE_ID, GROUP_ID, PASSWORD);
        tokenPayLoad.username(customer.customer().email()).password(PASSWORD);
        AddressPayLoad addressPayLoad = new AddressPayLoad().create();

        PrepareCheckoutPayLoad prepareCheckoutPayLoad = new PrepareCheckoutPayLoad()
                .setCheckoutData(addressPayLoad, shippingMethod, shippingCarrierCode);

        CreateOrderPayLoad createOrderPayLoad = new CreateOrderPayLoad()
                .setPaymentData(paymentSystem, addressPayLoad);

        userApiService.registerNewUser(customer);
        String token = tokenApiService.getUserToken(tokenPayLoad);
        String quoteId = quoteApiService.getQuoteId(token);
        addItemsToCartPayLoad.setProductToCart(productSku, productQty, quoteId);
        addItemsToCartApiService.addingProductToCart(addItemsToCartPayLoad, token);
        prepareCheckoutApiService.setShippingInformation(token, prepareCheckoutPayLoad);

        createOrderApiService
                .placeOrder(token, createOrderPayLoad)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body(Matchers.not(Matchers.empty()));
    }
}
