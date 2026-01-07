package com.demoshopp.base;

import org.testng.annotations.*;

import com.demoshopp.pageutilities.ConfigReader;
import com.demoshopp.pageutilities.WaitUtils;

public class Basepage {

    public WaitUtils waitUtils;  // utility for explicit waits

    // Runs before each test method
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        System.out.println(" [Basepage] Setting up driver for: " + browser);
        DriverManager.initDriver(browser);
        System.out.println(" [Basepage] Driver initialized: " + DriverManager.getDriver());
        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
        waitUtils = new WaitUtils(DriverManager.getDriver());
    }

    // Runs after each test method
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();  // close browser
    }
}