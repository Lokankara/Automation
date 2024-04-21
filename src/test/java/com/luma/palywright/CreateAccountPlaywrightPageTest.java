package com.luma.palywright;

import com.luma.model.User;
import com.luma.runner.LocatorPlayWright;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.luma.runner.TestData.MAGENTO_BASE_URL;

public class CreateAccountPlaywrightPageTest extends LocatorPlayWright {

    private static final Logger logger = LogManager.getLogger(CreateAccountPlaywrightPageTest.class);

    @Test
    void testSignInUser() {

        User user = new User();

        CreateAccountPlaywrightPage signIn = new CreateAccountPlaywrightPage(getPage());
        navigate(MAGENTO_BASE_URL);
        signIn.gotoSign();

        signIn.enterFirstName(user.getFirstname())
                .enterLastName(user.getLastname())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateAccountButton();

        String actualUser = signIn.getContactInformation();
        Assert.assertTrue(actualUser.contains(user.getFirstname()), "Text does not contain first name: " + user.getFirstname());
        Assert.assertTrue(actualUser.contains(user.getLastname()), "Text does not contain last name: " + user.getLastname());
        Assert.assertTrue(actualUser.contains(user.getEmail()), "Text does not contain email: " + user.getEmail());

        if (signIn.clickShevron().isLoggedIn()) {
            signIn.clickLogoutAccount().verifyLogOut();
            logger.info(signIn.getSignOutText());
        }
    }
}
