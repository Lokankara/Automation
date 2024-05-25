package com.luma.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AddressBookPage extends BasePage {

    private static final String url = "https://magento.softwaretestingboard.com/customer/address/new/";

    public AddressBookPage(
            WebDriver driver) {
        super(driver);
    }

    @Override
    BasePage open() {
        getDriver().get(url);
        return this;
    }

    @FindBy(xpath = "//span[@class='base' and @data-ui-id='page-title-wrapper']")
    public WebElement pageTitle;

    @FindBy(xpath = "//span[text() = 'Contact Information']")
    public WebElement spanContactInformation;

    @FindBy(id = "firstname")
    public WebElement inputFirstname;

    @FindBy(id = "lastname")
    public WebElement inputLastname;

    @FindBy(id = "company")
    public WebElement inputCompany;

    @FindBy(id = "telephone")
    public WebElement inputTelephone;

    @FindBy(xpath = "//*[text() = 'Address']")
    public WebElement spanAddress;

    @FindBy(id = "street_1")
    public WebElement inputStreet;

    @FindBy(id = "street_2")
    public WebElement inputStreet2;

    @FindBy(id = "street_3")
    public WebElement inputStreet3;

    @FindBy(id = "city")
    public WebElement inputCity;

    @FindBy(id = "region_id")
    public WebElement selectRegion;

    @FindBy(id = "zip")
    public WebElement inputZip;

    @FindBy(id = "country")
    public WebElement selectValidate;

    @FindBy(xpath = "//*[text() = 'Save Address']")
    public WebElement spanSaveAddress;

    @FindBy(xpath = "//div[@class='message-success success message']/div")
    public WebElement message;

    public AddressBookPage changeFirstName(String firstName) {
        inputFirstname.clear();
        inputFirstname.sendKeys(firstName);
        return this;
    }

    public AddressBookPage changeLastName(String lastName) {
        inputLastname.clear();
        inputLastname.sendKeys(lastName);
        return this;
    }

    public AddressBookPage fillCompanyName(String companyName) {
        inputCompany.clear();
        inputCompany.sendKeys(companyName);
        return this;
    }

    public AddressBookPage changePhoneNumber(String phoneNumber) {
        inputTelephone.clear();
        inputTelephone.sendKeys(phoneNumber);
        return this;
    }

    public AddressBookPage changeStreetAddress(String streetAddress) {
        inputStreet.clear();
        inputStreet.sendKeys(streetAddress);
        return this;
    }

    public AddressBookPage changeCity(String city) {
        inputCity.clear();
        inputCity.sendKeys(city);
        return this;
    }

    public AddressBookPage changeZipPostalCode(String zip) {
        inputZip.clear();
        inputZip.sendKeys(zip);
        return this;
    }

    public AddressBookPage selectRegionByValue(String regionValue) {
        selectRegion.click();
        getDriver().findElement(By.xpath("//select[@id='region_id']/option[@value='" + regionValue + "']")).click();
        return this;
    }

    public AddressBookPage fillStreetAddress2(String streetAddress2) {
        inputStreet2.sendKeys(streetAddress2);
        return this;
    }

    public AddressBookPage fillStreetAddress3(String streetAddress3) {
        inputStreet3.sendKeys(streetAddress3);
        return this;
    }

    public AddressBookPage selectCountryByName(String country) {
        new Select(selectValidate).selectByVisibleText(country);
        return this;
    }

    public void saveAddress() {
        spanSaveAddress.click();
    }

    public void isVisibleHeader() {
        Assert.assertTrue(pageTitle.isDisplayed());
    }

    public void isVisibleContactInformation() {
        Assert.assertTrue(spanContactInformation.isDisplayed());
    }

    public void isVisibleAddress() {
        Assert.assertTrue(spanAddress.isDisplayed());
    }

    public void isVisibleMessage() {
        Assert.assertEquals(message.getText(), "You saved the address.", "Success message does not match expected");
    }
}
