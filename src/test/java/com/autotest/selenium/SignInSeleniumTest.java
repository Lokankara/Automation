package com.autotest.selenium;

import com.autotest.runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.autotest.runner.TestData.MAGENTO_BASE_URL;

public class SignInSeleniumTest extends BaseTest {

    @Test
    public void testSignIn() {
        getDriver().get(MAGENTO_BASE_URL);
        getDriver().findElement(By.linkText("Sign In")).click();

        getDriver().findElement(By.id("email")).sendKeys("test+123@test.com");
        getDriver().findElement(By.id("pass")).sendKeys("Tester123");
        getDriver().findElement(By.id("send2")).click();
//        WebElement element = getDriver().findElement(By.xpath("//div[@class='panel header']//span[@class='logged-in']"));
//
//        Assert.assertTrue(element.isDisplayed());
    }

}
