package com.autotest.palywright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import com.autotest.runner.BaseTest;

import java.util.regex.Pattern;

import static com.autotest.runner.TestData.MAGENTO_BASE_URL;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NavigationPlaywrightPageTest extends BaseTest {

    @Test
    void testNavigateToMenuItems() {
        getDriver().quit();
        Page page = getPage();
        page.navigate(MAGENTO_BASE_URL);

        Pattern[] menuItems = {
                Pattern.compile("What's New"),
                Pattern.compile("Women"),
                Pattern.compile("Men"),
                Pattern.compile("Gear"),
                Pattern.compile("Training"),
                Pattern.compile("Sale")};
        String[] pageTitles = {"What's New", "Women", "Men", "Gear", "Training", "Sale"};
        String[] pageURLs = {
                MAGENTO_BASE_URL + "/what-is-new.html",
                MAGENTO_BASE_URL + "/women.html",
                MAGENTO_BASE_URL + "/men.html",
                MAGENTO_BASE_URL + "/gear.html",
                MAGENTO_BASE_URL + "/training.html",
                MAGENTO_BASE_URL + "/sale.html"
        };

        for (int i = 0; i < menuItems.length; i++) {
            page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(menuItems[i])).click();
            assertThat(page).hasTitle(pageTitles[i]);
            assertThat(page).hasURL(pageURLs[i]);
            page.goBack();
        }
    }
}
