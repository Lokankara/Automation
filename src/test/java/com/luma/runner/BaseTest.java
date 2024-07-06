package com.luma.runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected final static String ADMIN = "tester1234@gmail.com";
    public final static String ADMIN_PASS = "tester1234!";
    public final static int WEBSITE_ID = 1;
    public final static int GROUP_ID = 1;
    public final static String FIRST_NAME = "Automation";
    public final static String LAST_NAME = "Luma";
    public final static String PASSWORD = "Tester1234!";

    private WebDriver driver;
    private final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    @BeforeSuite
    protected void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    protected void setupDriver(@Optional("chrome") String browser, ITestContext context, ITestResult result) {
        Reporter.log("______________________________________________________________________", true);

        this.driver = DriverUtils.createDriver(browser, this.driver);
        this.threadLocalDriver.set(driver);

        Reporter.log("Test Thread ID: " + Thread.currentThread().getId(), true);
        Reporter.log("TEST SUIT: " + context.getCurrentXmlTest().getSuite().getName(), true);
        Reporter.log("RUN " + result.getMethod().getMethodName(), true);

        if (getDriver() == null) {
            Reporter.log("ERROR: Unknown parameter 'browser' - '" + browser + "'.", true);

            System.exit(1);
        }

        Reporter.log("INFO: " + browser.toUpperCase() + " driver created.", true);
    }

    @Parameters("browser")
    @AfterMethod(alwaysRun = true)
    protected void tearDown(@Optional("chrome") String browser, ITestResult result) {
        Reporter.log(result.getMethod().getMethodName() + ": " + ReportUtils.getTestStatus(result),
                true);

        if (getDriver() != null) {
            getDriver().quit();
            Reporter.log("INFO: " + browser.toUpperCase() + " driver closed.", true);

            Reporter.log("After Test Thread ID: " + Thread.currentThread().getId(), true);
            threadLocalDriver.remove();

            driver = null;

        } else {
            Reporter.log("INFO: Driver is null.", true);
        }
    }

    protected WebDriver getDriver() {
        return threadLocalDriver.get();
    }
}
