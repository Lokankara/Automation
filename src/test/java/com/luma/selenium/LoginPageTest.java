package com.luma.selenium;

import com.luma.runner.BaseSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.luma.runner.TestData.MAGENTO_BASE_URL;

public class LoginPageTest extends BaseSelenium {

    @Test
    public void testNavigationBar(){
        List<String> expectedListOfNavBar = List.of("What's New", "Women", "Men", "Gear", "Training","Sale");
        new MainPage(getDriver());
        List<WebElement> listOfNavBar = getDriver().findElements(By.cssSelector(".nav-sections .navigation >ul>li>a"));
        List<String> namesOfNavbar = listOfNavBar.stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertEquals(namesOfNavbar, expectedListOfNavBar);
    }
}
