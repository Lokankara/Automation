package com.luma.selenium;

import com.luma.runner.BaseSelenium;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


public class WishlistPageTest extends BaseSelenium {

    @Test
    @Ignore
    public void testAddRandomProductToWishlist() {
        WishlistPage wishlistPage =
                new LandingPage(getDriver())
                .clickMyAccountBtn()
                .clickSignIn().login(EMAIL, PASSWORD)
                .getNavBar()
                .selectCategory(MEN)
                .goToProductPage("Random")
                .wishlistProduct();
        Assert.assertTrue(wishlistPage.isMessageDisplayed());
    }
}
