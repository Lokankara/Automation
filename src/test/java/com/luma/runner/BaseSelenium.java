package com.luma.runner;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseSelenium {

    protected String MEN = "";
    protected String EMAIL = "";
    protected String PASSWORD = "";

    @Getter
    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        driver = BaseUtils.createDriver();
    }

    @AfterMethod
    protected void afterMethod(ITestResult testResult, Method testMethod) {
        if (driver != null) {
            driver.quit();
        }
    }
}
