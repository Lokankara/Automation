package com.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    private final WebDriver driver;
    protected static final String BASE_URL = "https://magento.softwaretestingboard.com/";

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
