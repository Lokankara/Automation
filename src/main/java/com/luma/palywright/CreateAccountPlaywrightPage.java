package com.luma.palywright;

import com.microsoft.playwright.Page;

public class CreateAccountPlaywrightPage {
    private final Page page;
    private final String passwordInputLocator = "input#password";
    private final String lastNameInputLocator = "input#lastname";
    private final String emailInputLocator = "input#email_address";
    private final String confirmPasswordInputLocator = "input#password-confirmation";
    private final String createAccountButtonLocator = "button[title='Create an Account']";
    private final String signup = "li > a[href='https://magento.softwaretestingboard.com/customer/account/create/']";

    public CreateAccountPlaywrightPage(Page page) {
        this.page = page;
    }

    public CreateAccountPlaywrightPage enterFirstName(String firstname) {
        page.fill("#firstname", firstname);
        return this;
    }

    public String getContactInformation() {
        return page.innerText(".box.box-information .box-content p");
    }

    public CreateAccountPlaywrightPage clickShevron() {
        page.click("button.action.switch");
        return this;
    }

    public CreateAccountPlaywrightPage clickLogoutAccount() {
        page.click("#logoutButton");
        return this;
    }

    public boolean getSignOutText() {
        return page.isVisible("#signOutText");
    }

    public boolean isLoggedIn() {
        return page.isVisible("#loggedInUserElement");
    }

    public void verifyLogOut() {
        if (!isLoggedIn()) {
            throw new IllegalStateException("User is not logged in.");
        }
        page.click("#logoutButton");
        page.waitForSelector("#loginButton");
    }

    public void gotoSign() {
        page.click(signup);
    }

    public CreateAccountPlaywrightPage enterLastName(String lastName) {
        page.fill(lastNameInputLocator, lastName);
        return this;
    }

    public CreateAccountPlaywrightPage enterEmail(String email) {
        page.fill(emailInputLocator, email);
        return this;
    }

    public CreateAccountPlaywrightPage enterPassword(String password) {
        page.fill(passwordInputLocator, password);
        return this;
    }

    public CreateAccountPlaywrightPage enterConfirmPassword(String confirmPassword) {
        page.fill(confirmPasswordInputLocator, confirmPassword);
        return this;
    }

    public void clickCreateAccountButton() {
        page.click(createAccountButtonLocator);
    }
}
