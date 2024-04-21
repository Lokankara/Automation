package com.autotest.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.autotest.runner.BaseTest;

public class MainPageTest extends BaseTest {

    @Test
    public void testMainPageElements() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.checkHref();

        Assert.assertTrue(mainPage.isStoreLogoDisplayed(), "Logo is not displayed");
        Assert.assertTrue(mainPage.isAddToCartButtonPresent(), "Add to cart button is not present");
        Assert.assertTrue(mainPage.isPromoSectionDisplayed(), "Promo section is not displayed");
        Assert.assertTrue(mainPage.areTrendingProductsDisplayed(), "Trending products are not displayed");
        Assert.assertTrue(mainPage.isSearchFieldPresent(), "Search field is not present");
        Assert.assertTrue(mainPage.isYogaCollectionPromoDisplayed(), "Yoga Collection promo is not displayed");
        Assert.assertTrue(mainPage.isPantsPromoDisplayed(), "Pants promo is not displayed");
        Assert.assertTrue(mainPage.isTShirtsPromoDisplayed(), "T-Shirts promo is not displayed");
        Assert.assertTrue(mainPage.isErinRecommendsPromoDisplayed(), "Erin Recommends promo is not displayed");
        Assert.assertTrue(mainPage.isPerformancePromoDisplayed(), "Performance promo is not displayed");
        Assert.assertTrue(mainPage.isEcoFriendlyPromoDisplayed(), "Eco-Friendly promo is not displayed");
    }
}
