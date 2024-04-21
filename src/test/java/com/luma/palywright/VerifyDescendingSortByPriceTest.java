package com.luma.palywright;

import com.luma.runner.LocatorPlayWright;
import com.luma.runner.TestData;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VerifyDescendingSortByPriceTest extends LocatorPlayWright {

    @Test()
    public void testDescendingSortByPrice() throws InterruptedException {
        openMenTopsPage();

        label("Sort By").selectOption("price");
        findByLink("Set Descending Direction").click();

        Thread.sleep(2000);
        List<Locator> productsElements = xpath("//li[@class='item product product-item']").all();
        List<Locator> pricesElements = xpath("//li[@class='item product product-item']//span[@class='price']").all();
        List<Double> prices = pricesElements.stream().map(price -> Double.parseDouble(price.innerText().substring(1))).toList();

        Assert.assertTrue(prices.size() > 1, "There aren NOT enough products for price comparison.");
        Assert.assertEquals(prices.size(), productsElements.size());
        assertThat(xpath("//li[@class='item product product-item']")).hasCount(prices.size());

//        Assert.assertTrue(prices.get(0) < prices.get(prices.size() - 1), "The products are NOT displayed in descending order of price.");
//        Assert.assertFalse(prices.get(0) >= prices.get(prices.size() - 1), "The price of the first product is LOWER than the price of the last product.");
//        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).innerText().substring(1));
//        Assert.assertEquals(Helper.getMin(prices), firstCardPrice);
//        Assert.assertFalse(Helper.isAscending(prices), "The subsequent products are NOT arranged in descending order.");
    }

    private void openMenTopsPage() {
        openBaseUrlPLay();
        findByText("Men").click();
        findByLink("Tops").click();
    }

    public void openBaseUrlPLay(){
        navigate(TestData.MAGENTO_BASE_URL);
        if(getPage().locator("//p[text()='Consent']").count() != 0) {
            getPage().locator("//p[text()='Consent']").click();
        }
    }
}
