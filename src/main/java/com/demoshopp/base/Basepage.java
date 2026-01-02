package com.demoshopp.base;

import org.testng.SkipException;
import org.testng.annotations.*;

import com.demoshopp.pageutilities.ConfigReader;
import com.demoshopp.pageutilities.WaitUtils;

public class Basepage {

    public WaitUtils waitUtils;  // utility for explicit waits

    // Runs before each test method
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        System.out.println("🔧 [Basepage] Setting up driver for: " + browser);

        // ✅ Skip Edge in Jenkinss to avoid headless crash
        if ((browser.equalsIgnoreCase("edge") || browser.equalsIgnoreCase("firefox"))
                && System.getenv("JENKINS_HOME") != null) {
            throw new SkipException("🚫 Skipping " + browser + " in Jenkins due to headless crash issue");
        }

        DriverManager.initDriver(browser);
        System.out.println("✅ [Basepage] Driver initialized: " + DriverManager.getDriver());

        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
        waitUtils = new WaitUtils(DriverManager.getDriver());
    }

    // Runs after each test method
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();  // close browser
    }
}