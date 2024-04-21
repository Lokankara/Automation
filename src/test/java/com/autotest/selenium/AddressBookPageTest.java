package com.autotest.selenium;

import com.autotest.model.Address;
import com.autotest.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.autotest.runner.BaseTest;

public class AddressBookPageTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(AddressBookPageTest.class);

    @Test
    public void testAddressBookPage() {
        User user = new User();
        Address address = new Address();
        MainPage mainPage = new MainPage(getDriver());

        mainPage.openPage();
        mainPage.clickCreateAnAccountButton()
                .enterFirstName(user.getFirstname())
                .enterLastName(user.getLastname())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateAccountButton();

        logger.error(getDriver().getTitle());
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
                .selectCountryByName("Australia") //Alaska
                .selectRegionByValue("569")
                .saveAddress();
        bookPage.isVisibleMessage();

        if (mainPage.clickShevron().isLoggedIn()) {
            mainPage.clickLogoutAccount().verifyLogOut();
            logger.error(mainPage.getSignOutText());
        }
    }
}
