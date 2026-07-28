package ui.base;

import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uiApi.utilities.ConfigReader;
import uiApi.utilities.WaitUtils;

public class Basepage {

    public WaitUtils waitUtils;  // utility for explicit waits
    private static final Logger logger = LogManager.getLogger(Basepage.class);
    public  String email;
    public String password;
    // Runs before each test method
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger.info("Setting up driver for: {}", browser);
        DriverManager.initDriver(browser);
        logger.info("Driver initialized: {}", DriverManager.getDriver());
        
        switch(browser) {
        
        case  "chrome" :   email=ConfigReader.getProperty("chrome_username");
                           password=ConfigReader.getProperty("chrome_password");
                           logger.info("Credentials loaded for Browser:{}",browser);
        break;
        	
        case  "firefox" : email=ConfigReader.getProperty("firefox_username");
                          password=ConfigReader.getProperty("firefox_password");
                          logger.info("Credentials loaded for Browser:{}",browser);
        break;
        case  "edge" : email=ConfigReader.getProperty("chrome_username");
                       password=ConfigReader.getProperty("chrome_password");
                       logger.info("Credentials loaded for Browser:{}",browser);
        break;
        default :
        	throw new RuntimeException("invalid Browser");
        
        }
        DriverManager.getDriver().get(ConfigReader.getProperty("ui.url"));
        logger.info("Navigated to URL: {}",ConfigReader.getProperty("ui.url") );
        waitUtils = new WaitUtils(DriverManager.getDriver());
    }

    // Runs after each test method
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Closing browser and quitting driver");
        DriverManager.quitDriver();
    }
}