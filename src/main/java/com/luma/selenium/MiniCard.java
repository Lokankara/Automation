package com.luma.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MiniCard {
    private final WebDriver driver;
    private final By update = By.cssSelector("[title='Update']");

    @FindBy(css = ".message-success")
    private WebElement message;

    @FindBy(css = "#ui-id-1")
    private WebElement miniCart;

    @FindBy(css = ".action.viewcart")
    private WebElement miniCartView;

    @FindBy(css = "a.action")
    private WebElement action;

    @FindBy(css = ".details-qty input")
    private WebElement quantity;

    public MiniCard(WebDriver driver) {
        this.driver = driver;
    }

    public void isMiniCartVisible() {
        Assert.assertTrue(miniCart.isDisplayed(), "Mini cart not visible!");
    }

    public void isMiniCartViewVisible() {
        Assert.assertTrue(miniCartView.isDisplayed(), "Mini cart view not visible!");
    }

    public void isMiniCartHaveLink(String cartUrl) {
        Assert.assertEquals(miniCartView.getAttribute("href"), cartUrl, "Mini cart view href not as expected!");
    }

    public void checkColorInTheMiniCart(String color) {
        String expectedColor = Color.fromString(color).asRgba();
        Assert.assertEquals(action.getCssValue("color"), expectedColor, "Color not as expected!");
    }

    public void checkSizeColorAndProductNameAreCorrect(By seeDetailsLocator, By sizeLocator, String size, By colorLocator, String color, By nameItemLocator, String name) {
        driver.findElement(seeDetailsLocator).click();
        Assert.assertEquals(getText(sizeLocator), size, "Size not as expected!");
        Assert.assertEquals(getText(colorLocator), color, "Color not as expected!");
        Assert.assertEquals(getText(nameItemLocator), name, "Product name not as expected!");
    }

    public void changeQty(String qty) {
        quantity.click();
        quantity.sendKeys(Keys.BACK_SPACE + qty);
        driver.findElement(update).click();
    }

    public void shouldBeQuantityChange(String qty) {
        Assert.assertEquals(quantity.getAttribute("value"), qty, "Quantity not as expected!");
    }

    public void shouldBeSuccessMessage() {
        Assert.assertTrue(message.isDisplayed(), "Success message not visible!");
    }

    public void shouldBeChangeSubtotal(By priceItem, String price, By cartSubtotal, String total) {
        Assert.assertEquals(getText(priceItem), price, "Price not as expected!");
        Assert.assertEquals(getText(cartSubtotal), total, "Subtotal not as expected!");
    }

    public void clickMiniCart() {
        Assert.assertTrue(miniCart.isEnabled(), "Mini cart not clickable!");
        miniCart.click();
    }

    private String getText(By locator) {
        return driver.findElement(locator).getText();
    }
}
