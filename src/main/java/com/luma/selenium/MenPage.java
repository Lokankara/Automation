package com.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MenPage extends BasePage {

    public static final String MEN_PAGE_URL = BASE_URL + "/men.html";

    public MenPage(WebDriver driver) {
        super(driver);
        open();
    }

    @Override
    BasePage open() {
        getDriver().get(MEN_PAGE_URL);
        return this;
    }

    public void assertUrl() {
        Assert.assertEquals(getCurrentUrl(), MEN_PAGE_URL,
                "The current URL does not match the expected Men's Page URL.");
    }
}
