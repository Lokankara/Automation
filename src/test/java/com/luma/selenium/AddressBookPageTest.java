package com.luma.selenium;

import com.luma.model.Address;
import com.luma.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import com.luma.runner.BaseSelenium;

public class AddressBookPageTest extends BaseSelenium {

    private static final Logger logger = LogManager.getLogger(AddressBookPageTest.class);

    @Test
    @Ignore
    public void testAddressBookPage() {
        User user = new User();
        Address address = new Address();
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickCreateAnAccountButton()
                .enterFirstName(user.getFirstname())
                .enterLastName(user.getLastname())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateAccountButton();

        logger.info(getDriver().getTitle());
        AddressBookPage bookPage = mainPage.gotoAddressBook();
        bookPage.isVisibleHeader();
        bookPage.isVisibleContactInformation();
        bookPage.isVisibleAddress();
        bookPage.changeFirstName(address.getFirstName())
                .changeLastName(address.getLastName())
                .fillCompanyName(address.getCompanyName())
                .changePhoneNumber(address.getPhoneNumber())
                .changeStreetAddress(address.getStreetAddress())
                .changeCity(address.getCity())
                .changeZipPostalCode(address.getZipPostalCode())
                .fillStreetAddress2(address.getStreetAddress())
                .fillStreetAddress3(address.getStreetAddress())
                .changeCity(address.getCity())
                .changeZipPostalCode(address.getZipPostalCode())
                .selectCountryByName("Australia")
                .selectRegionByValue("569")
                .saveAddress();
        bookPage.isVisibleMessage();

        if (mainPage.clickShevron().isLoggedIn()) {
            mainPage.clickLogoutAccount().verifyLogOut();
            logger.info(mainPage.getSignOutText());
        }
    }
}
