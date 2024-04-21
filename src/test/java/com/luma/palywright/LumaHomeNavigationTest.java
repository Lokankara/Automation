package com.luma.palywright;

import com.luma.runner.LocatorPlayWright;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

import static com.luma.runner.TestData.MAGENTO_BASE_URL;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LumaHomeNavigationTest extends LocatorPlayWright {
    private final List<String> ExpectedNamesWomenSection = List.of("Tops", "Bottoms");
    private final List<String> ExpectedNamesTopsSection = List.of("Jackets", "Hoodies & Sweatshirts", "Tees", "Bras & Tanks");
    private final List<String> ExpectedNamesBottomsSection = List.of("Pants", "Shorts");

    @Test
    public void testNavigationBar(){
        String [] expectedListOfNavBar = {"What's New", "Women", "Men", "Gear", "Training", " Sale"} ;

        getPage().getByLabel("Consent",new Page.GetByLabelOptions());
        navigate(MAGENTO_BASE_URL);
        Locator navBar = getPage().locator(".nav-sections .navigation >ul>li>a");

        assertThat(navBar).hasCount(6);
        assertThat(navBar).containsText(expectedListOfNavBar);
    }

    @Test
    public void testNamesWomenSection() {
        navigate(MAGENTO_BASE_URL);
        getPage().getByText("Women").hover();
        Locator listWomenSection = getPage().locator("li.nav-2>ul>li>a.ui-corner-all");
        List<String> namesWomenSection = listWomenSection.allInnerTexts();
        Locator listTopSection = getPage().locator(".nav-2-1>ul>li>a>span");
        List<String>namesTopSection = listTopSection.allInnerTexts();
        Locator listBottomSection = getPage().locator(".nav-2-2>ul>li");
        List<String> namesBottomsSection = listBottomSection.allInnerTexts();
        assertThat(listWomenSection).hasCount(2);
        assertThat(listTopSection).hasCount(4);
        assertThat(listBottomSection).hasCount(2);
        Assert.assertEquals(namesWomenSection, ExpectedNamesWomenSection);
        Assert.assertEquals(namesBottomsSection, ExpectedNamesBottomsSection);
        Assert.assertEquals(namesTopSection, ExpectedNamesTopsSection);
    }

    @Ignore
    @Test
    public void testWomenSectionButtonsClickable() throws InterruptedException {
        getPage().navigate(MAGENTO_BASE_URL);
        Locator listWomenSection = getPage().locator(".navigation li.nav-2>ul>li>a");
        Locator headerWomenSections = getPage().locator(".page-title");

        for (int i = 0; i < listWomenSection.count(); i++) {
            getPage().getByText("Women").hover();
            Thread.sleep(1000);
            listWomenSection.nth(i).click();
            String text = headerWomenSections.innerText();
            Assert.assertEquals(text, ExpectedNamesWomenSection.get(i));
            getPage().locator(".logo").click();
            assertThat(getPage()).hasURL(MAGENTO_BASE_URL+"/");
        }

        Locator listTopSection = getPage().locator("li.nav-2-1>ul>li>a");
        for ( int i = 0; i < listTopSection.count(); i++ ) {
            getPage().getByText("Women").hover();
            getPage().locator("#ui-id-9>.ui-icon-carat-1-e").hover();
            Thread.sleep(1000);
            listTopSection.nth(i).click();
            String text = headerWomenSections.innerText();
            Assert.assertEquals(text, ExpectedNamesTopsSection.get(i));
            getPage().locator(".logo").click();
            assertThat(getPage()).hasURL(MAGENTO_BASE_URL + "/");
        }
    }
}
