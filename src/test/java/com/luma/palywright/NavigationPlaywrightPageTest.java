package com.luma.palywright;

import com.luma.runner.LocatorPlayWright;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.luma.runner.TestData.MAGENTO_BASE_URL;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NavigationPlaywrightPageTest extends LocatorPlayWright {

    @Test
    void testNavigateToMenuItems() {
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
        String[] pageURLs = {"/what-is-new.html", "/women.html", "/men.html", "/gear.html", "/training.html", "/sale.html"};

        for (int i = 0; i < menuItems.length; i++) {
            page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(menuItems[i])).click();
            assertThat(page).hasTitle(pageTitles[i]);
            assertThat(page).hasURL(MAGENTO_BASE_URL + pageURLs[i]);
            page.goBack();
        }
    }
}
