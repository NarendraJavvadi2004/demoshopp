package ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import ui.enums.BrowserType;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get Driver
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver()");
        }
        return driver.get();
    }

    private static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    // Initialize Driver
    public static void initDriver(String browser) {

        WebDriver webDriver;
        BrowserType browserType;

        try {
            browserType = BrowserType.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        switch (browserType) {

            // ================= CHROME =================
            case CHROME:

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true);
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");

                webDriver = new ChromeDriver(chromeOptions);
                break;

            // ================= FIREFOX =================
            case FIREFOX:

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                firefoxOptions.addArguments("-headless");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            // ================= EDGE =================
            case EDGE:

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setAcceptInsecureCerts(true);
                edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--window-size=1920,1080");

                webDriver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalStateException("Unexpected browser: " + browserType);
        }

        webDriver.manage().window().maximize();
        setDriver(webDriver);
    }

    // Quit Driver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}