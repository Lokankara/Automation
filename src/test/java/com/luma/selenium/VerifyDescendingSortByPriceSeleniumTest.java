package com.luma.selenium;

import com.luma.runner.BaseSelenium;
import com.luma.runner.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.luma.runner.TestData.MAGENTO_BASE_URL;

public class VerifyDescendingSortByPriceSeleniumTest extends BaseSelenium {

    public void openBaseUrlSelenium() {
        getDriver().get(MAGENTO_BASE_URL);
        List<WebElement> consentElements = getDriver().findElements(By.xpath("//p[text()='Consent']"));
        if(!consentElements.isEmpty()) {getDriver().findElement(By.xpath("//p[text()='Consent']")).click();
        }
    }

    @Test
    public void testSelenium() {
        getDriver().get(MAGENTO_BASE_URL);

        Assert.assertEquals(getDriver().getCurrentUrl(), MAGENTO_BASE_URL+"/");
        Assert.assertEquals(getDriver().getTitle(), "Home Page");
    }

    @Test
    public void testDescendingSortByPrice() {

        getDriver().get(MAGENTO_BASE_URL);
        getDriver().findElement(By.linkText("Men")).click();
        getDriver().findElement(By.linkText("Tops")).click();
        getDriver().findElement(By.id("sorter")).click();
        getDriver().findElement(By.xpath("//option[@value='price']")).click();
        getDriver().findElement(By.linkText("Set Descending Direction")).click();

        List<WebElement> productsElements = getDriver().findElements(By.xpath("//li[@class='item product product-item']"));
        List<WebElement> pricesElements = getDriver().findElements(By.xpath("//li[@class='item product product-item']//span[@class='price']"));
        List<Double> prices = pricesElements.stream().map(price -> Double.parseDouble(price.getText().substring(1))).collect(Collectors.toList());

        Assert.assertTrue(prices.size() > 1, "There aren NOT enough products for price comparison.");
        Assert.assertEquals(prices.size(), productsElements.size());
        Assert.assertTrue(prices.get(0) >= prices.get(prices.size() - 1), "The products are NOT displayed in descending order of price.");
        Assert.assertFalse(prices.get(0) < prices.get(prices.size() - 1), "The price of the first product is LOWER than the price of the last product.");

        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).getText().substring(1));
        Assert.assertEquals(Helper.getMax(prices), firstCardPrice);
        Assert.assertTrue(Helper.isDescending(prices), "The subsequent products are NOT arranged in descending order.");
    }
}
