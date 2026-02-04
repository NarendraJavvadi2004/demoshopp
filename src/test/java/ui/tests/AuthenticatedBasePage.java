package ui.tests;

import org.testng.annotations.BeforeMethod;

import ui.base.Basepage;
import ui.base.DriverManager;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import uiApi.utilities.ConfigReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticatedBasePage extends Basepage {

    private static final Logger logger = LogManager.getLogger(AuthenticatedBasePage.class);

    @BeforeMethod
    public void loginOnce() {
        logger.info("Executing @BeforeMethod: loginOnce");

        HomePage home = new HomePage(DriverManager.getDriver());
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        if (home.isUserLoggedOut()) {
            logger.info("User is logged out. Navigating to Login page");
            home.clickLogin();

            String username = ConfigReader.getProperty("ui.username");
            logger.info("Logging in with configured username: {}", username);
            loginPage.login(username, ConfigReader.getProperty("ui.password"));

            logger.info("Login completed successfully for user: {}", username);
        } else {
            logger.info("User is already logged in. Skipping login step");
        }
    }
}