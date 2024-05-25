package com.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishlistPage extends BasePage {

    public static final String WISH_LIST_URL = "https://magento.softwaretestingboard.com/wishlist";

    @FindBy(className = "message-success")
    private WebElement productMessage;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }


    @Override
    BasePage open() {
        getDriver().get(WISH_LIST_URL);
        return this;
    }

    public boolean isMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productMessage));
        return productMessage.isDisplayed();
    }
}
