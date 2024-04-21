package com.luma.runner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.nio.file.Paths;
import java.util.Properties;

public final class BaseUtils {
    private static final String ENV_CHROME_OPTIONS = "CHROME_OPTIONS";
    private static final String ENV_BROWSER_OPTIONS = "BROWSER_OPTIONS";

    private static final String PREFIX_PROP = "default.";
    private static final String PROP_CHROME_OPTIONS = PREFIX_PROP + ENV_CHROME_OPTIONS.trim().toLowerCase();

    private static Properties properties;

    private static void initProperties() {
        if (properties == null) {
            properties = new Properties();
            if (isServerRun()) {
                properties.setProperty(PROP_CHROME_OPTIONS, System.getenv(ENV_CHROME_OPTIONS));
                if (System.getenv(ENV_BROWSER_OPTIONS) != null) {
                    for (String option : System.getenv(ENV_BROWSER_OPTIONS).split(";\n")) {
                        String[] browserOptionArr = option.split("=");
                        properties.setProperty(browserOptionArr[0], browserOptionArr[1]);
                    }
                }
            } else {
                try {
                    InputStream inputStream = BaseUtils.class.getClassLoader().getResourceAsStream("local.properties");
                    if (inputStream == null) {
                        System.out.println("ERROR: The \u001B[31mlocal.properties\u001B[0m file not found in src/test/resources/ directory.");
                        System.out.println("You need to create it from local.properties file.");
                        System.exit(1);
                    }
                    properties.load(inputStream);
                } catch (IOException ignore) {
                }
            }
        }
    }

    static boolean isServerRun() {
        return System.getenv("CI_RUN") != null;
    }

    private static final ChromeOptions chromeOptions;

    static {
        initProperties();

        chromeOptions = new ChromeOptions();
        String options = properties.getProperty(PROP_CHROME_OPTIONS);
        if (options != null) {
            for (String argument : options.split(";")) {
                chromeOptions.addArguments(argument);
            }
        }

        WebDriverManager.chromedriver().setup();
    }

    static Properties getProperties() {
        return properties;
    }

    static WebDriver createDriver() {
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.executeCdpCommand("Network.enable", Map.of());
        driver.executeCdpCommand("Network.setExtraHTTPHeaders", Map.of("headers", Map.of("accept-language", "en-US,en;q=0.9")));
        return driver;
    }

    private static final boolean IS_HEADLESS = Boolean.parseBoolean(properties.getProperty("isHeadless").trim());
    private static final double SLOWMO = Double.parseDouble(properties.getProperty("slowMo").trim());
    private static final int SCREEN_SIZE_WIDTH = Integer.parseInt(properties.getProperty("screenWidth").trim());
    private static final int SCREEN_SIZE_HEIGHT = Integer.parseInt(properties.getProperty("screenHeight").trim());

    static Browser createPWBrowser(Playwright playwright) {
        return playwright.chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(IS_HEADLESS).setSlowMo(SLOWMO));
    }

    static BrowserContext createContext(Browser browser) {
        return browser.newContext(new Browser.NewContextOptions()
                .setExtraHTTPHeaders(Map.of("Accept-Language", "en-US,en;q=0.9"))
                .setViewportSize(SCREEN_SIZE_WIDTH, SCREEN_SIZE_HEIGHT)
                .setRecordVideoSize(1280, 720)
                .setRecordVideoDir(Paths.get("video/"))
        );
    }
}
