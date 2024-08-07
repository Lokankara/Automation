package com.luma.page;

import com.luma.selenium.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BagsPage extends SideMenu {
    private final String productNameXpath = "//img[@alt='";

    protected BagsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage open() {
        return null;
    }

    @Step("Click '{productName}' Img.")
    public ProductPage clickProductImg(String productName) {
        String imgXpath = productNameXpath + productName + "']";
        getDriver().findElement(By.xpath(imgXpath)).click();

        return new ProductPage(getDriver());
    }
}

