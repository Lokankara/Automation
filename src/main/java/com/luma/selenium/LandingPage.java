package com.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    @FindBy(css = ".authorization-link + li a")
    private WebElement createAnAccountBtn;

    @FindBy(className = "logged-in")
    private WebElement loggedInMsg;

    @FindBy(className = "switch")
    private WebElement accountMenuBtn;

    @FindBy(css = ".customer-menu li:nth-of-type(1)")
    private WebElement myAccountBtn;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public NavBar getNavBar() {
        return new NavBar(getDriver());
    }

    @Override
    BasePage open() {
        getDriver().get(BASE_URL);
        return this;
    }

    public LoginPage clickCreateAnAccountButton() {
        createAnAccountBtn.click();
        return new LoginPage(getDriver());
    }

    public AccountPage clickMyAccountBtn() {
        clickAccountMenuBtn();
        myAccountBtn.click();
        return new AccountPage(getDriver());
    }

    public boolean isLoggedInMsgDisplayed() {
        return loggedInMsg.isDisplayed();
    }

    public void clickAccountMenuBtn() {
        accountMenuBtn.click();
    }
}
