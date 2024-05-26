package com.luma.selenium;

import com.luma.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.luma.runner.BaseSelenium;

public class SignUpPageTest extends BaseSelenium {

    @BeforeMethod
    public void beforeClass() {
        mainPage = new MainPage(getDriver());
    }

    private MainPage mainPage;

    @Test
    void testSignInUser() {

        User user = new User();
        mainPage.clickCreateAnAccountButton()
                .enterFirstName(user.getFirstname())
                .enterLastName(user.getLastname())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateAccountButton();
        String actualUser = mainPage.getContactInformation();
        Assert.assertTrue(actualUser.contains(user.getFirstname()), "Text does not contain first name: " + user.getFirstname());
        Assert.assertTrue(actualUser.contains(user.getLastname()), "Text does not contain last name: " + user.getLastname());
        Assert.assertTrue(actualUser.contains(user.getEmail()), "Text does not contain email: " + user.getEmail());

//        Assert.assertEquals(mainPage.confirmMessage(), "Thank you for registering with Main Website Store.", "Text does not match expected");

        if (mainPage.clickShevron().isLoggedIn()) {
            mainPage.clickLogoutAccount().verifyLogOut();
        }
    }
}
