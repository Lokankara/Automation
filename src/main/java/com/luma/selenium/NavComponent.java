package com.luma.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.luma.selenium.BasePage.BASE_URL;
import static com.luma.selenium.MenPage.MEN_PAGE_URL;

public class NavComponent {

    public NavComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String font = "rgba(51, 51, 51, 1)";

    @FindBy(css = "#ui-id-18")
    private WebElement navMenBottoms;

    @FindBy(css = "#ui-id-17")
    private WebElement navMenTops;

    @FindBy(css = "#ui-id-5")
    private WebElement navMen;

    @FindBy(css = "li.level1.nav-3-1.category-item.first.parent.ui-menu-item")
    private WebElement navMenSubmenu;

    @FindBy(css = "li.level1.nav-3-2.category-item.last.parent.ui-menu-item")
    private WebElement navMenBottomsSubmenu;
    public static final By NAV_MENU = By.cssSelector("#ui-id-2");
    public static final By NAV_MEN_TOPS_JACKET = By.cssSelector("#ui-id-19");
    public static final By NAV_MEN_TOPS_HOODIES = By.cssSelector("#ui-id-20");
    public static final By NAV_MEN_TOPS_TEES = By.cssSelector("#ui-id-21");
    public static final By NAV_MEN_TOPS_TANKS = By.cssSelector("#ui-id-22");
    public static final By NAV_MEN_TOPS_SUBMENU_HREFS = By.cssSelector(".nav-3-1 > ul > li > a");
    public static final By NAV_MEN_BOTTOMS_SUBMENU_HREFS = By.cssSelector(".nav-3-2 > ul > li > a");

    public static final Map<String, String> menTopUrls = new HashMap<>();
    public static final Map<String, String> menBottomUrls = new HashMap<>();
    public static final Map<String, String> menSubUrls = new HashMap<>();

    static {
        menTopUrls.put("Jackets", BASE_URL + "/men/tops-men/jackets-men.html");
        menTopUrls.put("Hoodies & Sweatshirts", BASE_URL + "/men/tops-men/hoodies-and-sweatshirts-men.html");
        menTopUrls.put("Tees", BASE_URL + "/men/tops-men/tees-men.html");

        menTopUrls.put("Tanks", BASE_URL + "/men/tops-men/tanks-men.html");
        menBottomUrls.put("Pants", BASE_URL + "/men/bottoms-men/pants-men.html");
        menBottomUrls.put("Shorts", BASE_URL + "/men/bottoms-men/shorts-men.html");

        menSubUrls.put("Tops", BASE_URL + "/men/tops-men.html");
        menSubUrls.put("Bottoms", BASE_URL + "/men/bottoms-men.html");
    }

    private final WebDriver driver;

    public void isClickable(WebElement element, String url) {
        Assert.assertTrue(element.isEnabled(), "Element not clickable!");
        Assert.assertEquals(element.getAttribute("href"), url, "URL not as expected!");
    }

    public void isHaveHeader(By headerLocator, String header) {
        WebElement headerElement = driver.findElement(headerLocator);
        Assert.assertEquals(headerElement.getText(), header, "Header text not as expected!");
    }

    public void verifySubTopNavUrl() {
        Assert.assertTrue(navMenSubmenu.isDisplayed(), "Submenus not visible!");
        for (String expectedElement : menTopUrls.keySet()) {
            Assert.assertTrue(navMenSubmenu.getText().contains(expectedElement), "Expected element not found!");
        }
    }

    public void verifySubBottomsNavUrl() {
        Assert.assertTrue(navMenBottomsSubmenu.isDisplayed(), "Submenus not visible!");
        for (String expectedElement : menBottomUrls.keySet()) {
            Assert.assertTrue(navMenBottomsSubmenu.getText().contains(expectedElement), "Expected element not found!");
        }
    }

    public void verifySubDropdownMenu() {
        verifyNavMensTops();
        verifyNavMensBottoms();
    }

    public void verifyNavMen() {
        verifyNav(navMen, "Men");
        isClickable(navMen, MEN_PAGE_URL);
    }

    public void verifyNavMensTops() {
        verifyNav(navMenTops, "Tops");
        isClickable(navMenTops, menSubUrls.get("Tops"));
    }

    public void verifyNavMensBottoms() {
        verifyNavMens(navMenBottoms, "Bottoms");
        isClickable(navMenBottoms, menSubUrls.get("Bottoms"));
    }

    private void verifyNavMens(WebElement element, String text) {
        verifyNav(element, text);
        isClickable(element, menSubUrls.get(text));
    }

    private void verifyNav(WebElement element, String title) {
        Assert.assertTrue(element.isDisplayed(), "Locator not visible!");
        Assert.assertEquals(element.getText(), title, "Text not as expected!");
    }

    private void hover(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public MenPage gotoMenPage() {
        navMen.click();
        return new MenPage(driver);
    }

    public void hoverToMen() {
        hover(navMen);
    }

    public void hoverToMenTop() {
        hover(navMenTops);
        isActiveColor(navMenTops);
    }

    public void hoverToMenBottom() {
        hover(navMenBottoms);
        isActiveColor(navMenBottoms);
    }
    protected void isActiveColor(WebElement element) {
        Assert.assertEquals(element.getCssValue("color"), font, "Font color not as expected!");
    }
}
