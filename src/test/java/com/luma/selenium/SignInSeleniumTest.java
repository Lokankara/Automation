package com.luma.selenium;

import com.luma.runner.BaseSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.luma.runner.TestData.MAGENTO_BASE_URL;

public class SignInSeleniumTest extends BaseSelenium {

    @Test
    public void testSignIn() {
        getDriver().get(MAGENTO_BASE_URL);
        getDriver().findElement(By.linkText("Sign In")).click();

        getDriver().findElement(By.id("email")).sendKeys("test+123@test.com");
        getDriver().findElement(By.id("pass")).sendKeys("Tester123");
        getDriver().findElement(By.id("send2")).click();
    }

    @Test
    public void testSign() {
        getDriver().get(MAGENTO_BASE_URL);
    }
}
