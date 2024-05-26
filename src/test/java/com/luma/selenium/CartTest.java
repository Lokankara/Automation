package com.luma.selenium;

import com.luma.runner.BaseSelenium;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseSelenium {

    @Test
    @Ignore
    public void testCartPricing() {
        final int productQuantity = 3;
        CartPage cartPage = new LandingPage(getDriver())
                .open()
                .clickSignIn()
                .login(EMAIL, PASSWORD)
                .getNavBar()
                .selectCategory("Women", "Bottoms", "Pants")
                .addProductsToCart(productQuantity)
                .getNavBar().open();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(cartPage.isProductSubtotalValid());
        softAssert.assertTrue(cartPage.isCartSubtotalValid());
        softAssert.assertTrue(cartPage.isTotalValid());
        softAssert.assertAll();
    }

    @Test
    @Ignore
    public void testCartQuantity() {
        final int addedQuantity = 3;
        CartPage cartPage = new LandingPage(getDriver())
                .gotoCartPage(By.name("//cart"))
                .openLandingPage()
                .getNavBar()
                .open();
        int initialQuantity = cartPage.getProductQuantity();
        MainPage storePage = cartPage.getNavBar(getDriver()).selectCategory(MEN);
        storePage.addProductsToCart(addedQuantity);
        cartPage = storePage.getNavBar().open();
        Assert.assertTrue(cartPage.isProductQuantityValid(initialQuantity, addedQuantity));
    }
}
