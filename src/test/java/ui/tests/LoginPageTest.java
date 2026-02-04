package ui.tests;

import ui.base.Basepage;
import ui.base.DriverManager;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import uiApi.utilities.DataP;
import uiApi.utilities.RetryAnalyzer;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPageTest extends Basepage {

    private static final Logger logger = LogManager.getLogger(LoginPageTest.class);

    @Test(
        dataProvider = "loginData",
        dataProviderClass = DataP.class,
        groups = {"Sanity", "Regression"},
        retryAnalyzer = RetryAnalyzer.class
    )
    public void testValidLogin(String email, String password) {
        logger.info("Starting test: testValidLogin with email: {}", email);

        HomePage home = new HomePage(DriverManager.getDriver());
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("Navigating to Login page");
        home.clickLogin();

        logger.info("Attempting login with email: {}", email);
        loginPage.login(email, password);

        String actualEmail = loginPage.getLoggedInUserEmail();
        logger.info("Validating logged in user email. Expected: {}, Actual: {}", email, actualEmail);

        Assert.assertEquals(actualEmail, email, "Login email mismatch!");

        logger.info("Login test passed for user: {}", email);

        loginPage.clickLogout();
        logger.info("User logged out successfully");

        logger.info("Test completed: testValidLogin");
    }
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	