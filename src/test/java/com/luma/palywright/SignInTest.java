package com.luma.palywright;

import com.luma.runner.LocatorPlayWright;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static com.luma.runner.TestData.MAGENTO_BASE_URL;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SignInTest extends LocatorPlayWright {
    private static final Logger logger = LoggerFactory.getLogger(SignInTest.class);

    @Test
    public void testSingIn() {
        logger.info("Navigating to the website");
        getPage().navigate(MAGENTO_BASE_URL);

        String consentButtonSelector = "body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-footer-buttons-container > div.fc-footer-buttons > button.fc-button.fc-cta-consent.fc-primary-button > p";
        logger.info("Checking for visibility of consent button");

        if (getPage().isVisible(consentButtonSelector)) {
            handleOverlays(getPage());
            getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign In ")).click();
            logger.info("Consent overlay handled and 'Sign In' clicked");
            assertThat(getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true))).isVisible();

        } else {
            getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign In ")).click();
            logger.info("'Sign In' clicked without overlay");
        }

        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).click();
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("tester1234@gmail.com");
        assertThat(getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In"))).isEnabled();

        getPage().getByLabel("Password").click();
        getPage().getByLabel("Password").fill("tester1234!");
        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
    }

    private void handleOverlays(Page page) {
        String consentButtonSelector = "body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-footer-buttons-container > div.fc-footer-buttons > button.fc-button.fc-cta-consent.fc-primary-button > p";
        page.waitForSelector(consentButtonSelector);

        if (!page.isVisible(consentButtonSelector)) {
            return;
        }
        page.click(consentButtonSelector);
    }
}

