package com.luma.runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.nio.file.Paths;

public class BasePlayWright {

    private Page page;
    private BrowserContext context;
    private final Playwright playwright = Playwright.create();
    private final Browser browser = BaseUtils.createPWBrowser(playwright);

    @BeforeSuite
    void createPlaywrightBrowser() {
        if(!browser.isConnected()) {
            System.exit(1);
        }
    }

    @BeforeMethod
    protected void beforeMethod() {
        context = BaseUtils.createContext(browser);
        page = context.newPage();
    }

    @AfterMethod
    protected void afterMethod(ITestResult testResult, Method testMethod) {

        if (page != null) {
            page.close();
        }

        if (!testResult.isSuccess()) {
            page.video().saveAs(Paths.get("video/" + testMethod.getName() + ".webm"));
            page.video().delete();
        } else {
            page.video().delete();
        }

        if (context != null) {
            context.close();
        }
    }

    @AfterSuite(alwaysRun = true)
    void closeBrowser() {
        if (browser.isConnected()) {
            browser.close();
        }
        if(playwright != null) {
            playwright.close();
        }
    }

    public Page getPage() {
        return page;
    }
}
