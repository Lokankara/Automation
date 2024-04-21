package com.luma.selenium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.luma.runner.BaseSelenium;

public class SearchBoxTest extends BaseSelenium {

    @Test
    public void testFindBackPack() {
        getDriver().get("https://magento.softwaretestingboard.com");
        getDriver().findElement(By.xpath("//input[@name='q']")).sendKeys("back");
    }
}
