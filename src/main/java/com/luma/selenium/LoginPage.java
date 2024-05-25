package com.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {
    String url = "https://magento.softwaretestingboard.com/customer/account/login";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.open();
    }

    @Override
    BasePage open() {
        getDriver().get(url);
        return this;
    }

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "pass")
    private WebElement passField;

    @FindBy(id = "send2")
    private WebElement signInBtn;

    public MainPage login(String email, String password) {
        fillEmailField(email);
        fillPasswordField(password);
        clickSignInButton();
        return new MainPage(getDriver());
    }

    private void fillEmailField(String email) {
        emailField.sendKeys(email);
    }

    private void fillPasswordField(String password) {
        passField.sendKeys(password);
    }

    private void clickSignInButton() {
        signInBtn.click();
    }
}
