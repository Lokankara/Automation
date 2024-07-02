package com.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {

    String url = "https://magento.softwaretestingboard.com/";

    @FindBy(css = ".swatch-option.text")
    private List<WebElement> sizeOptions;

    @FindBy(css = ".swatch-option.color")
    private List<WebElement> colorOptions;

    @FindBy(className = "towishlist")
    private WebElement toWishlistBtn;

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//li[contains(@class, 'item category')][last()]/a")
    private WebElement categoryBtn;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage open() {
        getDriver().get(url);
        return this;
    }

    public WishlistPage wishlistProduct() {
        toWishlistBtn.click();
        return new WishlistPage(getDriver());
    }

    public ProductsPage addProductToCart() {
        chooseFirstSize();
        chooseFirstColor();
        addToCartBtn.click();
        return this;
    }

    public void chooseFirstSize() {
        sizeOptions.get(0).click();
    }

    public void chooseFirstColor() {
        colorOptions.get(0).click();
    }

    public void goBackToCategory() {
        categoryBtn.click();
    }
}
