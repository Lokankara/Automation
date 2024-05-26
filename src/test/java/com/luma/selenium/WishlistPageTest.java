package com.luma.selenium;

import com.luma.runner.BaseSelenium;
import org.jetbrains.annotations.NotNull;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


public class WishlistPageTest extends BaseSelenium {

    @Test
    @Ignore
    public void testAddRandomProductToWishlist() {
        WishlistPage wishlistPage = getLandingPage()
                .getNavBar()
                .selectCategory(MEN)
                .goToProductPage("Random")
                .wishlistProduct();
        Assert.assertTrue(wishlistPage.isMessageDisplayed());
    }

    @Test
    public void testAddProductToWishlist() {
        WishlistPage wishlistPage = getLandingPage()
                .getNavBar()
                .selectCategory("Men", "Tops", "Tees")
                .goToProductPage("Zoltan Gym Tee")
                .wishlistProduct();
        Assert.assertTrue(wishlistPage.isMessageDisplayed());
    }

    @NotNull
    private MainPage getLandingPage() {
        return new LandingPage(getDriver())
                .clickMyAccountBtn()
                .clickSignIn().login(EMAIL, PASSWORD);
    }
}
