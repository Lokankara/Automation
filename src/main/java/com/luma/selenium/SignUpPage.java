package com.luma.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    private static final String SIGNUP_URL = "https://magento.softwaretestingboard.com/customer/account/create/";

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    BasePage open() {
        getDriver().get(SIGNUP_URL);
        return this;
    }

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='email_address']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='password-confirmation']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@title='Create an Account']")
    private WebElement createAccountButton;

    public SignUpPage enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public SignUpPage enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public SignUpPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public SignUpPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public SignUpPage enterConfirmPassword(String confirmPassword) {
        confirmPasswordInput.sendKeys(confirmPassword);
        return this;
    }

    public void clickCreateAccountButton() {
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView(true);",
                createAccountButton);
        createAccountButton.click();
    }
}
