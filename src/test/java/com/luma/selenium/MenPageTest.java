package com.luma.selenium;

import com.luma.runner.BaseSelenium;
import org.testng.annotations.Test;

public class MenPageTest extends BaseSelenium {

    @Test
    public void testVerifyingMenLinkIsDisplayedClickableRedirectionInTheMainPage() {
        MainPage page = new MainPage(getDriver());
        page.verifyMenNav();
        MenPage men = page.gotoMenPage();
        men.assertUrl();
        men.verifyMenNav();
    }
}
