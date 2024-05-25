package com.luma.selenium;

import org.openqa.selenium.WebDriver;

public class CustomerPage extends BasePage {

    protected CustomerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    BasePage open() {
        getDriver().get(BASE_URL);
        return this;
    }
}
