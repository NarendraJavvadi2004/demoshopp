package com.demoshopp.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.demoshopp.enums.BrowserType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    private static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void initDriver(String browser) {
        WebDriver webDriver;
        BrowserType browserType;

        try {
            browserType = BrowserType.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        switch (browserType) {

            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--window-size=1920,1080");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                try {
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("headless");
                    edgeOptions.addArguments("disable-gpu");
                    edgeOptions.addArguments("window-size=1920,1080");
                    webDriver = new EdgeDriver(edgeOptions);
                } catch (Exception e) {
                    System.setProperty("webdriver.edge.driver",
                        System.getProperty("user.dir") + "/drivers/msedgedriver.exe");
                    EdgeOptions fallbackOptions = new EdgeOptions();
                    fallbackOptions.addArguments("headless");
                    fallbackOptions.addArguments("disable-gpu");
                    fallbackOptions.addArguments("window-size=1920,1080");
                    webDriver = new EdgeDriver(fallbackOptions);
                }
                break;

            default:
                throw new IllegalStateException("Unexpected browser: " + browserType);
        }

        setDriver(webDriver);
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}