package com.autotest.palywright;

import org.testng.annotations.Test;

import static com.autotest.runner.TestData.MAGENTO_BASE_URL;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomeTest extends PlayWright {

    @Test
    public void testPlaywrightHome() {
        navigate(MAGENTO_BASE_URL);
        assertThat(getPage()).hasURL(MAGENTO_BASE_URL+"/");
        assertThat(getPage()).hasTitle("Home Page");
    }
}
