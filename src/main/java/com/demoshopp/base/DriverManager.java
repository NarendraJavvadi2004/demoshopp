package com.demoshopp.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
                webDriver = new ChromeDriver();
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;

            case EDGE:
                try {
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                } catch (Exception e) {
                    System.setProperty(
                        "webdriver.edge.driver",
                        System.getProperty("user.dir") + "/drivers/msedgedriver.exe"
                    );
                    webDriver = new EdgeDriver();
                }
                break;

            default:
                throw new IllegalStateException("Unexpected browser: " + browserType);
        }

        webDriver.manage().window().maximize();
        setDriver(webDriver);
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
