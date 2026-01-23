package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.Basepage;
import ui.base.DriverManager;
import ui.pages.HomePage;
import ui.pages.RegisterPage;
import uiApi.utilities.DataP;
import uiApi.utilities.RetryAnalyzer;

public class RegisterTest extends Basepage {
	
    @Test(dataProvider = "registerData",
    		dataProviderClass = DataP.class,
    		groups = {"Sanity","Regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserCanRegisterSuccessfully(String gender, String firstName, String lastName, String password) {
        RegisterPage registerPage = new RegisterPage(DriverManager.getDriver());
        HomePage home = new HomePage(DriverManager.getDriver());
        home.clickRegister();

        // Generate unique email every run
        String email = firstName.toLowerCase() + System.currentTimeMillis() + "@testmail.com";

        // Perform registration
        registerPage.registerUser(gender, firstName, lastName, email, password);

        // Assert success message
        String expectedMessage = "Your registration completed";
        String actualMessage = registerPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Registration success message mismatch!");
    }
}