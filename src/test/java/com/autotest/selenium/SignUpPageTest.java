package com.autotest.selenium;

import com.autotest.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.autotest.runner.BaseTest;

public class SignUpPageTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SignUpPageTest.class);

    @BeforeMethod
    public void beforeClass() {
        getPage().close();
        mainPage = new MainPage(getDriver());
    }

    private MainPage mainPage;

    @Test
    void testSignInUser() {
        final String expectedText = "Thank you for registering with Main Website Store.";
        //logger.info(mainPage.getDriver().getTitle());

        User user = new User();
        mainPage.openPage();
        mainPage.clickCreateAnAccountButton()
                .enterFirstName(user.getFirstname())
                .enterLastName(user.getLastname())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateAccountButton();
        //logger.info(mainPage.getDriver().getTitle());
        String actualUser = mainPage.getContactInformation();
        Assert.assertTrue(actualUser.contains(user.getFirstname()), "Text does not contain first name: " + user.getFirstname());
        Assert.assertTrue(actualUser.contains(user.getLastname()), "Text does not contain last name: " + user.getLastname());
        Assert.assertTrue(actualUser.contains(user.getEmail()), "Text does not contain email: " + user.getEmail());
        String confirmation = mainPage.confirmMessage();
        Assert.assertEquals(confirmation, expectedText, "Text does not match expected");

        if (mainPage.clickShevron().isLoggedIn()) {
            mainPage.clickLogoutAccount().verifyLogOut();
            //logger.info(mainPage.getSignOutText());
        }
    }
}
