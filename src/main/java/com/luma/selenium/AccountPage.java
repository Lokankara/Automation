package com.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AccountPage extends BasePage {
    private static final String url = "https://magento.softwaretestingboard.com/customer/account/";

    @FindBy(xpath = "//div[@class='box-content']//p")
    private WebElement accountInfoParagraph;

    protected AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    BasePage open() {
        getDriver().get(url);
        return this;
    }

    public String getAccountInfoText() {
        return accountInfoParagraph.getText();
    }

    private void verify(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    public void assertParagraphText(String expectedText) {
        verify(getAccountInfoText(), expectedText);
    }
}
