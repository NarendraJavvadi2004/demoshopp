package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.Basepage;
import ui.base.DriverManager;
import ui.pages.HomePage;
import ui.pages.RegisterPage;
import uiApi.utilities.DataP;
import uiApi.utilities.RetryAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterTest extends Basepage {

    private static final Logger logger = LogManager.getLogger(RegisterTest.class);

    @Test(dataProvider = "registerData",
          dataProviderClass = DataP.class,
          groups = {"Sanity","Regression"},
          retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserCanRegisterSuccessfully(String gender, String firstName, String lastName, String password) {
        logger.info("Starting test: verifyUserCanRegisterSuccessfully with data -> Gender: {}, FirstName: {}, LastName: {}",
                    gender, firstName, lastName);

        RegisterPage registerPage = new RegisterPage(DriverManager.getDriver());
        HomePage home = new HomePage(DriverManager.getDriver());

        logger.info("Navigating to Register page");
        home.clickRegister();

        // Generate unique email every run
        String email = firstName.toLowerCase() + System.currentTimeMillis() + "@testmail.com";
        logger.info("Generated unique email for registration: {}", email);

        // Perform registration
        logger.info("Performing registration for user: {} {}", firstName, lastName);
        registerPage.registerUser(gender, firstName, lastName, email, password);

        // Assert success message
        String expectedMessage = "Your registration completed";
        String actualMessage = registerPage.getSuccessMessage();
        logger.info("Validating registration success message. Expected: '{}', Actual: '{}'", expectedMessage, actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage, "Registration success message mismatch!");

        logger.info("Test completed: verifyUserCanRegisterSuccessfully");
    }
}