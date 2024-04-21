package com.luma.palywright;

import com.luma.runner.LocatorPlayWright;
import org.testng.annotations.Test;

import static com.luma.runner.TestData.MAGENTO_BASE_URL;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomeTest extends LocatorPlayWright {

    @Test
    public void testPlaywrightHome() {
        navigate(MAGENTO_BASE_URL);
        assertThat(getPage()).hasURL(MAGENTO_BASE_URL+"/");
        assertThat(getPage()).hasTitle("Home Page");
    }
}
