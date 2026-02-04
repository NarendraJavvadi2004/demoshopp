package ui.base;

import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uiApi.utilities.ConfigReader;
import uiApi.utilities.WaitUtils;

public class Basepage {

    public WaitUtils waitUtils;  // utility for explicit waits
    private static final Logger logger = LogManager.getLogger(Basepage.class);

    // Runs before each test method
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger.info("Setting up driver for: {}", browser);
        DriverManager.initDriver(browser);
        logger.info("Driver initialized: {}", DriverManager.getDriver());
        String url = ConfigReader.getProperty("ui.url");
        DriverManager.getDriver().get(url);
        logger.info("Navigated to URL: {}", url);
        waitUtils = new WaitUtils(DriverManager.getDriver());
    }

    // Runs after each test method
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Closing browser and quitting driver");
        DriverManager.quitDriver();
    }
}