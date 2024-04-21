package com.autotest.palywright;

import com.autotest.runner.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public abstract class PlayWright extends BaseTest {

    protected Locator findByText(String text) {
        return getPage().getByText(text, new Page.GetByTextOptions().setExact(true));
    }

    protected Locator findByLink(String text) {
        return getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(text));
    }

    protected Locator css(String css) {
        return getPage().locator(css);
    }

    protected Locator xpath(String xpath) {
        return getPage().locator(xpath);
    }

    protected void navigate(String url) {
        getPage().navigate(url);
    }

    protected Locator label(String label) {
        return getPage().getByLabel(label);
    }

    protected Locator title(String title) {
        return getPage().getByTitle(title);
    }
}
