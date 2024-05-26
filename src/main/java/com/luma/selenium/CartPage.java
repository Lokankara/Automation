package com.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class CartPage extends BasePage {
    private static final String CART_URL = "https://magento.softwaretestingboard.com/checkout/cart/";

    @FindBy(className = "item-info")
    private List<WebElement> productList;

    @FindBy(css = ".price .cart-price .price")
    private List<WebElement> productPrices;

    @FindBy(css = ".input-text.qty")
    private List<WebElement> productQuantities;

    @FindBy(css = ".subtotal .cart-price .price")
    private List<WebElement> productSubtotals;

    @FindBy(css = "td[data-th='Discount'] .price")
    private List<WebElement> discount;

    @FindBy(css = ".totals-tax .price")
    private List<WebElement> tax;

    @FindBy(css = ".sub .price")
    private WebElement cartSubtotal;

    @FindBy(css = ".grand .price")
    private WebElement grandTotal;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    BasePage open() {
        getDriver().get(CART_URL);
        return this;
    }

    public NavBar getNavBar(WebDriver driver) {
        return new NavBar(driver);
    }

    public int getProductQuantity() {
        return productQuantities.stream()
                .mapToInt(productQuantity -> Integer.parseInt(productQuantity.getAttribute("value")))
                .sum();
    }

    public boolean isProductSubtotalValid() {
        return IntStream.range(
                0, productList.size()).noneMatch(i -> extractDouble(productPrices.get(i).getText())
                * Integer.parseInt(productQuantities.get(i).getAttribute("value"))
                != extractDouble(productSubtotals.get(i).getText()));
    }

    public boolean isCartSubtotalValid() {
        return productSubtotals.stream()
                .mapToDouble(productSubtotal -> extractDouble(productSubtotal.getText()))
                .sum() == extractDouble(cartSubtotal.getText());
    }

    public boolean isProductQuantityValid(int initialQuantity, int addedQuantity) {
        return initialQuantity + addedQuantity == getProductQuantity();
    }

    public boolean isTotalValid() {
        double discountValue = !discount.isEmpty() ? extractDouble(discount.get(0).getText()) : 0;
        double taxValue = !tax.isEmpty() ? extractDouble(tax.get(0).getText()) : 0;
        return extractDouble(cartSubtotal.getText()) + discountValue + taxValue ==
                extractDouble(grandTotal.getText());
    }

    public static String extractMoneyValue(String text) {
        try {
            return NumberFormat.getCurrencyInstance(Locale.US).parse(text).toString();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public double extractDouble(String text) {
        return Double.parseDouble(extractMoneyValue(text));
    }

    public LandingPage openLandingPage() {
        return new LandingPage(getDriver());
    }
}
