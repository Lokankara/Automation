package com.autotest.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        this.openPage();
    }

    @FindBy(xpath = "//a[@class='logo']")
    private WebElement storeLogo;

    @FindBy(css = ".block-promo.home-main")
    private WebElement yogaCollectionPromo;

    @FindBy(css = ".block-promo.home-pants")
    private WebElement pantsPromo;

    @FindBy(css = ".block-promo.home-t-shirts")
    private WebElement tShirtsPromo;

    @FindBy(css = ".block-promo.home-erin")
    private WebElement erinRecommendsPromo;

    @FindBy(css = ".block-promo.home-performance")
    private WebElement performancePromo;

    @FindBy(css = ".block-promo.home-eco")
    private WebElement ecoFriendlyPromo;

    @FindBy(css = ".blocks-promo")
    private WebElement promoSection;

    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy(css = ".product-item")
    private List<WebElement> trendingProducts;

    @FindBy(css = ".action.tocart.primary")
    private WebElement addToCartButton;

    @FindBy(css = "li.authorization-link a")
    private WebElement signInBtn;

    @FindBy(css = ".authorization-link + li a")
    private WebElement createAnAccountBtn;

    @FindBy(className = "logged-in")
    private WebElement loginMessage;

    @FindBy(className = "switch")
    private WebElement accountMenuBtn;

    @FindBy(xpath = "//div[@class='panel header']//ul[@class='header links']//button")
    private WebElement shevron;

    @FindBy(xpath = "//div[@class='box-content']/p")
    private WebElement contactInformation;

    @FindBy(xpath = "//li[@class='customer-welcome active']//a[contains(@href, 'customer/account/logout/')]")
    private List<WebElement> dropdown;

    @FindBy(xpath = "//div[@role='alert']/div/div")
    private WebElement alert;

    @FindBy(xpath = "//span[@class='base' and @data-ui-id='page-title-wrapper']")
    private WebElement signOut;

    @FindBy(xpath = "//li[@class='customer-welcome active']//a[contains(@href, 'customer/account/logout/')]")
    private WebElement logoutAccount;

    @FindBy(css = ".customer-menu li:nth-of-type(1)")
    private WebElement myAccountBtn;

    @FindBy(xpath = "//a[text() = 'Address Book']")
    public WebElement linkAddressBook;

    public MainPage clickShevron() {
        shevron.click();
        return this;
    }

    public AddressBookPage gotoAddressBook() {
        linkAddressBook.click();
        return new AddressBookPage(getDriver());
    }

    public String confirmMessage() {
        return alert.getText().trim();
    }

    public boolean isLoggedIn() {
        return !dropdown.isEmpty();
    }

    public String getContactInformation() {
        return contactInformation.getText();
    }

    public void openPage() {
        getDriver().get(BASE_URL);
    }

    public LoginPage clickSignIn() {
        signInBtn.click();
        return new LoginPage(getDriver());
    }

    public SignUpPage clickCreateAnAccountButton() {
        createAnAccountBtn.click();
        return new SignUpPage(getDriver());
    }

    public AccountPage clickMyAccountButton() {
        accountMenuBtn.click();
        myAccountBtn.click();
        return new AccountPage(getDriver());
    }

    public void verifyLogOut() {
        Assert.assertEquals(getSignOutText(), "You are signed out");
    }

    public String getSignOutText() {
        return signOut.getText();
    }

    public MainPage clickLogoutAccount() {
        logoutAccount.click();
        return this;
    }

    public boolean isLoggedInMessageDisplayed() {
        return loginMessage.isDisplayed();
    }

    public boolean isStoreLogoDisplayed() {
        return storeLogo.isDisplayed();
    }

    public boolean isEcoFriendlyPromoDisplayed() {
        return ecoFriendlyPromo.isDisplayed();
    }

    public boolean isPerformancePromoDisplayed() {
        return performancePromo.isDisplayed();
    }

    public boolean isErinRecommendsPromoDisplayed() {
        return erinRecommendsPromo.isDisplayed();
    }

    public boolean isTShirtsPromoDisplayed() {
        return tShirtsPromo.isDisplayed();
    }

    public boolean isYogaCollectionPromoDisplayed() {
        return yogaCollectionPromo.isDisplayed();
    }

    public boolean isPantsPromoDisplayed() {
        return pantsPromo.isDisplayed();
    }

    public boolean isSearchFieldPresent() {
        return searchField.isDisplayed();
    }

    public boolean areTrendingProductsDisplayed() {
        return !trendingProducts.isEmpty();
    }

    public boolean isPromoSectionDisplayed() {
        return promoSection.isDisplayed();
    }

    public boolean isAddToCartButtonPresent() {
        return addToCartButton.isDisplayed();
    }

    public void checkHref() {
        Assert.assertEquals(yogaCollectionPromo.getAttribute("href"), BASE_URL + "collections/yoga-new.html");
        Assert.assertEquals(pantsPromo.getAttribute("href"), BASE_URL + "promotions/pants-all.html");
        Assert.assertEquals(tShirtsPromo.getAttribute("href"), BASE_URL + "promotions/tees-all.html");
        Assert.assertEquals(erinRecommendsPromo.getAttribute("href"), BASE_URL + "collections/erin-recommends.html");
        Assert.assertEquals(performancePromo.getAttribute("href"), BASE_URL + "collections/performance-fabrics.html");
        Assert.assertEquals(ecoFriendlyPromo.getAttribute("href"), BASE_URL + "collections/eco-friendly.html");
    }
}
